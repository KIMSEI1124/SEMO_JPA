package com.multi.semo.auth.exception;

import com.multi.semo.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum AuthErrorCode implements ErrorCode {
    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;

    AuthErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
