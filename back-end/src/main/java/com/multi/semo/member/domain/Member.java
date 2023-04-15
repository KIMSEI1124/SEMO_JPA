package com.multi.semo.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
