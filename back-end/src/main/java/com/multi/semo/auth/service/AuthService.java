package com.multi.semo.auth.service;

import com.multi.semo.auth.domain.AuthRepository;
import com.multi.semo.auth.domain.RefreshToken;
import com.multi.semo.auth.dto.request.LoginRequest;
import com.multi.semo.auth.dto.response.TokenResponse;
import com.multi.semo.auth.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final AuthRepository authRepository;

    public TokenResponse login(LoginRequest request) {
        /* Login ID/PW 를 기반으로 authenticationToken 생성 */
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        /*
         * 실제로 검증이 이루어지는 부분
         * authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
         */
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        /* 인증 정보를 generateTokenDto JWT 토큰 생성 */
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication);

        /* RefreshToken 을 저장 */
        RefreshToken token = RefreshToken.builder()
                .memberId(Long.valueOf(authentication.getName()))
                .value(tokenResponse.getRefreshToken())
                .build();
        authRepository.save(token);

        return tokenResponse;
    }
}
