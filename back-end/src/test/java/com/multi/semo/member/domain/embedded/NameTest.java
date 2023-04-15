package com.multi.semo.member.domain.embedded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class NameTest {

    @DisplayName("사용자의 이름이 조건에 부합하면 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 30})
    void isValid(int loop) {
        String value = "김".repeat(loop);
        assertThatCode(() -> Name.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("사용자의 이름이 올바르지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"세ㅁ", "ㅔ모"})
    void isNotValid(String value) {
        assertThatThrownBy(() -> Name.of(value))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("올바르지 않은 이름의 형식입니다.");
    }

    @DisplayName("사용자의 이름이 한국어가 아니면 단어가 포함되면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"semo", "kim", "3상", "@기"})
    void notKoreanName(String value) {
        assertThatThrownBy(() -> Name.of(value))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("올바르지 않은 이름의 형식입니다.");
    }


    @DisplayName("사용자의 이름의 길이가 2미만이거나 30을 초과할 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void lengthOutOfRange(int loop) {
        String value = "이".repeat(loop);
        assertThatThrownBy(() -> Name.of(value))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("사용자의 이름은 2자 이상 30자 이하여야 합니다.");
    }
}