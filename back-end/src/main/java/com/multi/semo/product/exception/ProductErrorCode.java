package com.multi.semo.product.exception;

import com.multi.semo.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum ProductErrorCode implements ErrorCode {
    INFO_CANNOT_BE_OUT_OF_RANGE(400, "PRODUCT_01", "상세 정보는 최대 1,000자까지 입력 가능합니다."),
    PRODUCT_NAME_DUPLICATED(400, "PRODUCT_02", "제품의 이름은 중복될 수 없습니다."),
    PRODUCT_IS_NOT_EXIST(400, "PRODUCT_03", "해당 제품이 존재하지 않습니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    ProductErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
