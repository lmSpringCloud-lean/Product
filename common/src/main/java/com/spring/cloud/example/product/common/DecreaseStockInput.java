package com.spring.cloud.example.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 购物车具体信息
 */
public class DecreaseStockInput {
    //  商品ID
    private String productId;
    //  商品数量
    private Integer productQuantity;
}
