package com.spring.cloud.example.product.service;

import com.spring.cloud.example.product.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ProductCategoryServiceTest extends ProductInfoServiceTest {

    @Autowired
    private IProductCategoryService productCategoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryService.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(list.size() > 0);
        for (ProductCategory category : list) {
            log.info(category.toString());
        }
    }
}