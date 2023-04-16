package com.multi.semo.auth.exception;

import com.multi.semo.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum AuthErrorCode implements ErrorCode {
    TOKEN_WITHOUT_AUTHORIZATION_INFORMATION(400, "AUTH_01", "권한 정보가 없는 토큰입니다."),
    JWT_SIGNATURE_MUST_BE_VALID(400, "AUTH_02", "잘못된 JWT 서명입니다."),
    JWT_MUST_BE_NOT_EXPIRED(400, "AUTH_03", "만료된 JWT 토큰입니다"),
    JWT_NOT_SUPPORT(400, "AUTH_04", "지원되지 않는 JWT 토큰입니다"),
    JWT_BE_MUST_VALID(400, "AUTH_05", "잘못된 JWT 토큰입니다."),
    AUTH_MUST_BE_VALID(401, "AUTH_06", "올바르지 않은 인증입니다."),
    AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE(403, "AUTH_07", "요청하신 기능에 접근할 권한이 없습니다."),
    CONTEXT_HAS_NOT_CONTAIN_AUTH_INFORMATION(401, "AUTH_08", "인증 정보가 없습니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    AuthErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
