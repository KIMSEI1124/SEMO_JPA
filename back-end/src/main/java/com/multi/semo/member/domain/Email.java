package com.multi.semo.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private static final String EMAIL_FORMAT = "[0-9a-z]+(.[_a-z0-9-]{15})*@(?:\\w{63}+\\.)+\\w+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_FORMAT);

    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        validatePatternIsValid(value);
        return new Email(value);
    }

    private static void validatePatternIsValid(String value) {
        if (isNotValid(value)) {
            throw new RuntimeException("올바르지 않은 이메일 형식입니다.");
        }
    }

    private static boolean isNotValid(String value) {
        return !EMAIL_PATTERN.matcher(value).matches();
    }
}
