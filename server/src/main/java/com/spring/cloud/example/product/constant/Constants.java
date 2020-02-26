package com.spring.cloud.example.product.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Constants {
    /**
     * 商品上下架状态
     */
    @Getter
    @AllArgsConstructor
    enum ProductStatus {
        UP(0,"在架"),
        DOWN(1,"下架"),
        ;
        private final Integer code;
        private final String msg;
    }

    /**
     * 错误码
     */
    @Getter
    @AllArgsConstructor
    enum Result {
        SUCCESS(0,"成功"),
        PRODUCT_NOT_EXIST(1,"商品不存在"),
        PRODUCT_STOCK_ERROR(2,"库存有误"),
        ;
        private final Integer code;
        private final String msg;
    }
}
