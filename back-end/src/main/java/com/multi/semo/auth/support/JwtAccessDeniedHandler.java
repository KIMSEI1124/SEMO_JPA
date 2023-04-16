package com.multi.semo.auth.support;

import com.multi.semo.auth.exception.AuthErrorCode;
import com.multi.semo.auth.exception.AuthException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    /* 필요한 권한이 없이 접근하려 할 때 403 */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        throw new AuthException(AuthErrorCode.AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE);
    }
}
