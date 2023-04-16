package com.multi.semo.member.domain.embedded;

import com.multi.semo.member.exception.MemberErrorCode;
import com.multi.semo.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {
    /* TODO: 길이 검증 분리하기 */
    private static final String PASSWORD_FORMAT =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_FORMAT);

    @Column(name = "password")
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
            throw new MemberException(MemberErrorCode.PASSWORD_PATTERN_MUST_BE_VALID);
        }
    }

    private static boolean isNotValid(String value) {
        return !PASSWORD_PATTERN.matcher(value).matches();
    }
}
