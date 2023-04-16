package com.multi.semo.member.domain;

import com.multi.semo.member.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.multi.semo.member.fixture.MemberFixture.SEMA;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class MemberTest {

    private static final MemberFixture MEMBER_FIXTURE = SEMA;

    @DisplayName("회원을 생성한다.")
    @Test
    void 회원을_생성한다() {
        assertThatCode(MEMBER_FIXTURE::toMember)
                .doesNotThrowAnyException();
    }
}