package com.spring.cloud.example.product.exception;


import com.spring.cloud.example.product.constant.Constants;

public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public ProductException(Constants.Result result) {
        super(result.getMsg());
        this.code = result.getCode();
    }
}
