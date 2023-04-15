package com.multi.semo.member.domain.embedded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PasswordTest {

    /* TODO: 추후 SecurityConfig 로 생성시 주입하여 사용 */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @DisplayName("사용자의 비밀번호가 조건에 부합하면 암호화한다.")
    @ParameterizedTest
    @ValueSource(strings = {"Password12!", "pass12Word!@"})
    void isValid(String value) {
        assertThatCode(() -> Password.encode(value, passwordEncoder()))
                .doesNotThrowAnyException();
    }

    @DisplayName("사용자의 비밀번호는 영어대문자, 숫자, 특수문자가 1개 이상 없을시 예외를 던진다.")
    @ParameterizedTest
    /* 영어대문자, 특수문자, 숫자, 영어소문자없을시 */
    @ValueSource(strings = {"password12!", "Password12", "Password!", "P123456789@"})
    void isNotValid(String value) {
        Throwable throwable = catchThrowable(() -> Password.encode(value, passwordEncoder()));
        assertThat(throwable)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("올바르지 않은 비밀번호 형식입니다.");
    }

    @DisplayName("사용자의 비밀번호는 8자 미만, 16자 초과이면 예외를 던진다.")
    @ParameterizedTest
    /* 7, 17 */
    @ValueSource(ints = {4, 14})
    void lengthOutOfRange(int loop) {
        String value = "A0!" + "a".repeat(loop);
        Throwable throwable = catchThrowable(() -> Password.encode(value, passwordEncoder()));
        assertThat(throwable)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("올바르지 않은 비밀번호 형식입니다.");
    }
}