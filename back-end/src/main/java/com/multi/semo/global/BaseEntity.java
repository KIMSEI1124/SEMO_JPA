package com.multi.semo.global;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
