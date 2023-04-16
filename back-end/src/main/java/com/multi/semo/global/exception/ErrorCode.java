package com.multi.semo.global.exception;

public interface ErrorCode {
    int getStatusCode();

    String getErrorCode();

    String getMessage();
}
