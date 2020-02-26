package com.spring.cloud.example.product.service;

import com.spring.cloud.example.product.dataobject.ProductCategory;

import java.util.List;

public interface IProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn (List<Integer> categoryTypeList);
}
