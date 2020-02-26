package com.spring.cloud.example.product.service;

import com.spring.cloud.example.product.ProductApplicationTests;
import com.spring.cloud.example.product.common.DecreaseStockInput;
import com.spring.cloud.example.product.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ProductInfoServiceTest extends ProductApplicationTests {
    @Autowired
    IProductInfoService productInfoService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productInfoService.findUpAll();
        for (ProductInfo productInfo : list) {
            log.info(productInfo.toString());
        }
    }

    @Test
    public void productInfoList() {
        List<ProductInfo> list = productInfoService.productInfoList(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(list.size() > 0);
        for (ProductInfo info : list) {
            log.info(info.toString());
        }
    }

    @Test
    public void decreaseStock() {
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022", 2);
        boolean result = productInfoService.decreaseStock(Arrays.asList(cartDTO));
        Assert.assertTrue(result);
    }
}