package com.multi.semo.global;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
