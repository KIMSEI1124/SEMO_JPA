package com.multi.semo.member.domain;

import lombok.Getter;

@Getter
public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }
}
