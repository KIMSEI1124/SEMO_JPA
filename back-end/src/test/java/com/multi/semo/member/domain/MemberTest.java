package com.multi.semo.member.domain;

import com.multi.semo.member.domain.embedded.Password;
import com.multi.semo.member.fixture.MemberFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.multi.semo.member.fixture.MemberFixture.SEMA;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberTest {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final MemberFixture MEMBER_FIXTURE = SEMA;

    @DisplayName("회원을 생성한다.")
    @Test
    void 회원을_생성한다() {
        Member member = MEMBER_FIXTURE.toMember();

        Assertions.assertAll(
                () -> assertThat(member.getBirth()).isEqualTo(SEMA.getBirth()),
                () -> assertThat(member.getName()).isEqualTo(SEMA.getName()),
                () -> assertThat(member.getPhone()).isEqualTo(SEMA.getPhone()),
                () -> {
                    Password password = Password.encode(SEMA.getPassword(), passwordEncoder);
                    assertThat(member.getPassword()).isEqualTo(password.getValue());
                },
                () -> assertThat(member.getPhone()).isEqualTo(SEMA.getPhone())
        );
    }
}