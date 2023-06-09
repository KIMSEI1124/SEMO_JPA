package com.multi.semo.member.exception;

import com.multi.semo.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements ErrorCode {
    EMAIL_PATTERN_MUST_BE_VALID(400, "EMAIL_01", "올바르지 않은 이메일 형식입니다."),
    EMAIL_ID_CANNOT_BE_OUT_OF_RANGE(400, "EMAIL_02", "이메일 계정의 길이는 최소 8자부터 최대 16자까지 허용합니다."),
    EMAIL_DOMAIN_NAME_CANNOT_BE_OUT_OF_RANGE(400, "EMAIL_03", "도메인 주소의 길이는 최소 2자부터 최대 63자까지 허용합니다."),
    NAME_PATTERN_MUST_BE_VALID(400, "NAME_01", "올바르지 않은 이름의 형식입니다."),
    NAME_CANNOT_BE_OUT_OF_RANGE(400, "NAME_02", "사용자의 이름은 2자 이상 30자 이하여야 합니다."),
    PASSWORD_PATTERN_MUST_BE_VALID(400, "PASSWORD_01", "올바르지 않은 비밀번호 형식입니다."),
    PHONE_PATTERN_MUST_BE_VALID(400, "PHONE_01", "올바르지 않은 전화번호 형식입니다."),
    SIGNUP_INVALID_EMAIL(400, "SIGNUP_01", "이미 가입된 이메일입니다."),
    SIGNUP_INVALID_PHONE(400, "SIGNUP_02", "이미 가입된 전화번호입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    MemberErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
