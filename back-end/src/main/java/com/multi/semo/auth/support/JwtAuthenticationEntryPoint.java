package com.multi.semo.auth.support;

import com.multi.semo.auth.exception.AuthErrorCode;
import com.multi.semo.auth.exception.AuthException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /* 유효한 자격증명을 제공하지 않고 접근하려 할때 401 응답 */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        throw new AuthException(AuthErrorCode.AUTH_MUST_BE_VALID);
    }
}
