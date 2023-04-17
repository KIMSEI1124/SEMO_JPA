package com.multi.semo.auth.domain;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* 60초 * 60분 * 24시간 * 14일 */
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
public class RefreshToken {
    @Id
    private Long id;
    private String value;

    @Builder
    public RefreshToken(Long memberId, String value) {
        this.id = memberId;
        this.value = value;
    }

    public RefreshToken update(String value) {
        this.value = value;
        return this;
    }
}
