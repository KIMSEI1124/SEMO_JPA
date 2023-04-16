package com.multi.semo.auth.exception;

import com.multi.semo.global.exception.ErrorCode;
import com.multi.semo.global.exception.SemoException;

public class AuthException extends SemoException {
    public AuthException(ErrorCode code) {
        super(code);
    }
}
