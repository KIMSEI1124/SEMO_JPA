package com.multi.semo.member.domain.embedded;

import com.multi.semo.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PhoneTest {

    @DisplayName("사용자의 전화번호 통신사번호가 조건에 부합하면 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "6", "7", "8", "9"})
    void isValid(String carrierNumber) {
        String value = "01" + carrierNumber + "12345678";
        assertThatCode(() -> Phone.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("사용자의 전화번호 통신사번호가 조건에 부합하지않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5"})
    void isNotValidByCarrierNumber(String carrierNumber) {
        String value = "01" + carrierNumber + "12345678";
        assertThatThrownBy(() -> Phone.of(value))
                .isInstanceOf(MemberException.class)
                .hasMessageContaining("올바르지 않은 전화번호 형식입니다.");
    }

    @DisplayName("전화번호에 숫자가 아닌 문자가 오면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "일", "!"})
    void hasNotNumberContain(String word) {
        String value = "0101234567" + word;
        assertThatThrownBy(() -> Phone.of(value))
                .isInstanceOf(MemberException.class)
                .hasMessageContaining("올바르지 않은 전화번호 형식입니다.");
    }

    @DisplayName("전화번호의 길이가 11자리 미만 12자리 초과이면 예외를 던진다.")
    @ParameterizedTest
    /* 10, 13 */
    @ValueSource(ints = {6, 9})
    void lengthOutOfRange(int loop) {
        String value = "010" + "1".repeat(loop);
        assertThatThrownBy(() -> Phone.of(value))
                .isInstanceOf(MemberException.class)
                .hasMessageContaining("올바르지 않은 전화번호 형식입니다.");
    }
}