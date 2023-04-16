package com.multi.semo.member.exception;

import com.multi.semo.global.exception.ErrorCode;
import com.multi.semo.global.exception.SemoException;

public class MemberException extends SemoException {
    public MemberException(ErrorCode code) {
        super(code);
    }
}
