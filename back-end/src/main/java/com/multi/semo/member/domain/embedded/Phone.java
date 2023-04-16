package com.multi.semo.member.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Phone {
    /* TODO: 버그 발생 수정 필요 */
    private static final String PHONE_FORMAT = "^01(?:0|1|[6-9])(?:\\\\d{3}|\\\\d{4})\\\\d{4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_FORMAT);

    @Column(name = "phone")
    private String value;

    private Phone(String value) {
        this.value = value;
    }

    public static Phone of(String value) {
        validatePatternIsValid(value);
        return new Phone(value);
    }

    private static void validatePatternIsValid(String value) {
        if (isNotValid(value)) {
            throw new RuntimeException("올바르지 않은 전화번호 형식입니다.");
        }
    }

    private static boolean isNotValid(String value) {
        return !PHONE_PATTERN.matcher(value).matches();
    }
}
