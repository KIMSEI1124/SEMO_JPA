package com.multi.semo.product.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Info {

    private final int MAX_LENGTH = 1000;

    @Lob
    @Column(name = "info")
    private String value;

    private Info(String value) {
        this.value = value;
    }

    public Info of(String value) {
        validateLengthInRange(value);
        return new Info(value);
    }

    private void validateLengthInRange(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new RuntimeException();
        }
    }
}
