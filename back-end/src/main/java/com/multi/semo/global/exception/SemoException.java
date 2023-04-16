package com.multi.semo.global.exception;

import lombok.Getter;

@Getter
public class SemoException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;
    private final String message;

    public SemoException(ErrorCode code) {
        this.statusCode = code.getStatusCode();
        this.errorCode = code.getErrorCode();
        this.message = code.getMessage();
    }
}
