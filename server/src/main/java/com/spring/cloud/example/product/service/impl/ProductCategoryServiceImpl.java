package com.spring.cloud.example.product.service.impl;

import com.spring.cloud.example.product.dataobject.ProductCategory;
import com.spring.cloud.example.product.repository.ProductCategoryRepository;
import com.spring.cloud.example.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    ProductCategoryRepository defaultRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return defaultRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
