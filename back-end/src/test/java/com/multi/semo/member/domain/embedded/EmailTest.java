package com.multi.semo.member.domain.embedded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class EmailTest {

    /* 버그 발생 */
    @DisplayName("사용자의 이메일가 조건에 부합하면 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"apple1234@gmail.com", "hello19jpa@naver.com"})
    void isValidEmail(String value) {
        assertThatCode(() -> Email.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("이메일의 @ 앞자리는 최소 8자리부터 최대16자리가 아니면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"apple12@gmail.com", "hello1234spring7@naver.com"})
    void lengthOutOfRange(String value) {
        assertThatThrownBy(() -> Email.of(value))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("올바르지 않은 이메일 형식입니다.");
    }
}