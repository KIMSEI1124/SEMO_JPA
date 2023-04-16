package com.multi.semo.auth.support;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    /**
     * 실제 필터링 로직은 해당 메서드에 있습니다.<br/>
     * JWT 토크의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역활을 수행합니다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /* 1. Request Header 에서 토큰을 꺼내는 작업 */
        String jwt = resolveToken(request);

        /*
         * 2. validateToken 으로 토큰 유효성 검사를 진행
         * 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
         */
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Request Header 에서 토큰 정보를 꺼내기
     *
     * @param request 인증 요청 정보
     * @return 토큰 정보
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
