package com.multi.semo.product.exception;

import com.multi.semo.global.exception.ErrorCode;
import com.multi.semo.global.exception.SemoException;

public class ProductException extends SemoException {
    public ProductException(ErrorCode code) {
        super(code);
    }
}
