package com.multi.semo.member.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Name {
    private static final String NAME_FORMAT = "^([가-힣]{30})*$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_FORMAT);
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 30;

    private String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String value) {
        validatePatternIsValid(value);
        validateLengthInRange(value);
        return new Name(value);
    }

    private static void validatePatternIsValid(String value) {
        if (isNotValid(value)) {
            throw new RuntimeException("올바르지 않은 이름의 형식입니다.");
        }
    }

    private static boolean isNotValid(String value) {
        return !NAME_PATTERN.matcher(value).matches();
    }

    private static void validateLengthInRange(String value) {
        int length = value.length();
        if (length < MIN_LENGTH || MAX_LENGTH < length) {
            throw new RuntimeException("사용자의 이름은 2자 이상 30자 이하여야 합니다.");
        }
    }

}
