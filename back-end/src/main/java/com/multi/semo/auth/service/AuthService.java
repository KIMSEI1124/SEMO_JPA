package com.multi.semo.auth.service;

import com.multi.semo.auth.domain.AuthRepository;
import com.multi.semo.auth.domain.RefreshToken;
import com.multi.semo.auth.dto.request.LoginRequest;
import com.multi.semo.auth.dto.request.RefreshTokenRequest;
import com.multi.semo.auth.dto.response.TokenResponse;
import com.multi.semo.auth.exception.AuthErrorCode;
import com.multi.semo.auth.exception.AuthException;
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

    public TokenResponse reissue(RefreshTokenRequest request) {
        /* Refresh Token 검증 */
        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new AuthException(AuthErrorCode.REFRESH_TOKEN_MUST_BE_VALID);
        }

        /* Access Token 에서 Member ID 가져오기 */
        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());
        Long memberId = Long.valueOf(authentication.getName());

        /* 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져오기 */
        RefreshToken refreshToken = authRepository.findById(memberId)
                .orElseThrow(() -> new AuthException(AuthErrorCode.MEMBER_IS_ALREADY_LOGOUT));

        /* Refresh Token 일치하는지 검사 */
        if (!refreshToken.getValue().equals(request.getRefreshToken())) {
            throw new AuthException(AuthErrorCode.TOKEN_MEMBER_INFORMATION_IS_NOT_SAME);
        }

        /* 새로운 토큰 생성 */
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication);

        /* 저장소 정보 업데이트 */
        RefreshToken newRefreshToken = refreshToken.update(tokenResponse.getRefreshToken());
        authRepository.save(newRefreshToken);

        return tokenResponse;
    }
}
