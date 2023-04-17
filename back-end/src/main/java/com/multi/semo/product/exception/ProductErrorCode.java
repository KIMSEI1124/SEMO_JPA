package com.multi.semo.product.exception;

import com.multi.semo.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum ProductErrorCode implements ErrorCode {
    INFO_CANNOT_BE_OUT_OF_RANGE(400, "PRODUCT_01", "상세 정보는 최대 1,000자까지 입력 가능합니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    ProductErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
