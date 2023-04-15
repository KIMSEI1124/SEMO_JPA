package com.multi.semo.member.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {
    private static final String PASSWORD_FORMAT =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_FORMAT);

    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password encode(String value, PasswordEncoder encoder) {
        validatePatternIsValid(value);
        return new Password(encoder.encode(value));
    }

    private static void validatePatternIsValid(String value) {
        if (isNotValid(value)) {
            throw new RuntimeException("올바르지 않은 비밀번호 형식입니다.");
        }
    }

    private static boolean isNotValid(String value) {
        return !PASSWORD_PATTERN.matcher(value).matches();
    }
}
