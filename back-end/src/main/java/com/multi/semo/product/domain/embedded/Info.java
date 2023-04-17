package com.multi.semo.product.domain.embedded;

import com.multi.semo.product.exception.ProductErrorCode;
import com.multi.semo.product.exception.ProductException;
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

    private static final int MAX_LENGTH = 1000;

    @Lob
    @Column(name = "info")
    private String value;

    private Info(String value) {
        this.value = value;
    }

    public static Info of(String value) {
        validateLengthInRange(value);
        return new Info(value);
    }

    private static void validateLengthInRange(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new ProductException(ProductErrorCode.INFO_CANNOT_BE_OUT_OF_RANGE);
        }
    }
}
