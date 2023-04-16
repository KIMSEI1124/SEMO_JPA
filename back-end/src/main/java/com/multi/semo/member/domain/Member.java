package com.multi.semo.member.domain;

import com.multi.semo.member.domain.embedded.Email;
import com.multi.semo.member.domain.embedded.Name;
import com.multi.semo.member.domain.embedded.Password;
import com.multi.semo.member.domain.embedded.Phone;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate birth;

    @Embedded
    private Email email;

    @Embedded
    private Name name;

    @Embedded
    private Password password;

    @Embedded
    private Phone phone;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Builder
    public Member(LocalDate birth, Email email, Name name, Password password, Phone phone) {
        this.birth = birth;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = Roles.USER;
    }

    /* Getter */
    public String getEmail() {
        return email.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getPhone() {
        return phone.getValue();
    }
}
