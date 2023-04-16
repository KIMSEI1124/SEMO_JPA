package com.multi.semo.member.fixture;

import com.multi.semo.member.domain.Member;
import com.multi.semo.member.domain.embedded.Email;
import com.multi.semo.member.domain.embedded.Name;
import com.multi.semo.member.domain.embedded.Password;
import com.multi.semo.member.domain.embedded.Phone;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Getter
public enum MemberFixture {
    SEMA(LocalDate.now(), "semo1234@gmail.com", "세모", "Semopw12!@", "01012345678");

    private final LocalDate birth;
    private final String email;
    private final String name;
    private final String password;
    private final String phone;

    MemberFixture(LocalDate birth, String email, String name, String password, String phone) {
        this.birth = birth;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public Member toMember() {
        return new Member(birth,
                Email.of(email),
                Name.of(name),
                Password.encode(password, new BCryptPasswordEncoder()),
                Phone.of(phone));
    }
}
