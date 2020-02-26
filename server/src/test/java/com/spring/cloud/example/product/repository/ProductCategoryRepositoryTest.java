package com.spring.cloud.example.product.repository;

import com.spring.cloud.example.product.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> param = Arrays.asList(11,22);
        List<ProductCategory> list = repository.findByCategoryTypeIn(param);
        for (ProductCategory category : list) {
            log.info(category.toString());
        }
    }
}