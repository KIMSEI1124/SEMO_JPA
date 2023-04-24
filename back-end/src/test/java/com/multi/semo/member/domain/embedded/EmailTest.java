package com.multi.semo.member.domain.embedded;

import com.multi.semo.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {
    @DisplayName("사용자의 이메일이 조건에 부합하면 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"semo1234@gmail.com", "projectsemo12345@naver.com"})
    void isValidEmail(String value) {
        assertThatCode(() -> Email.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("이메일의 앞자리는 최소 8자리부터 최대16자리가 아니면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {6, 16})
    void lengthOutOfRangeByPrefix(int loop) {
        /* 첫번째 테스트 이메일의 길이 : 7, 두번째 테스트 이메일의 길이 : 17 */
        String email = "a".repeat(loop) + "1@google.com";
        assertThatThrownBy(() -> Email.of(email))
                .isInstanceOf(MemberException.class)
                .hasMessageContaining("이메일 계정의 길이는 최소 8자부터 최대 16자까지 허용합니다.");
    }

    @DisplayName("이메일의 도메인 주소는 최소 2자리부터 최대63자리가 아니면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 64})
    void lengthOutOfRangeByDomainName(int loop) {
        String email = "semo1234@" + "a".repeat(loop) + ".com";
        assertThatThrownBy(() -> Email.of(email))
                .isInstanceOf(MemberException.class)
                .hasMessageContaining("도메인 주소의 길이는 최소 2자부터 최대 63자까지 허용합니다.");
    }

    @DisplayName("이메일의 주소는 영어 소문자와 숫자로 구성이 안되어있으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"SEMo1234@gmail.com", "semo!234@gmail.com", "semo1234@Gmail.com", "semo1234@gma!l.com"})
    void isNotValidEmail(String value) {
        assertThatThrownBy(() -> Email.of(value))
                .isInstanceOf(MemberException.class)
                .hasMessageContaining("올바르지 않은 이메일 형식입니다.");
    }
}