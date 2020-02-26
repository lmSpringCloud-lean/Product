package com.spring.cloud.example.product.service;

import com.spring.cloud.example.product.common.DecreaseStockInput;
import com.spring.cloud.example.product.dataobject.ProductInfo;

import java.util.List;

public interface IProductInfoService {
    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据商品ID，查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> productInfoList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     * @return
     */
    Boolean decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
