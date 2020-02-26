package com.spring.cloud.example.product.controller;

import com.spring.cloud.example.product.VO.ProductInfoVO;
import com.spring.cloud.example.product.VO.ProductVO;
import com.spring.cloud.example.product.VO.ResultVO;
import com.spring.cloud.example.product.common.DecreaseStockInput;
import com.spring.cloud.example.product.common.ProductInfoOutput;
import com.spring.cloud.example.product.dataobject.ProductCategory;
import com.spring.cloud.example.product.dataobject.ProductInfo;
import com.spring.cloud.example.product.service.IProductCategoryService;
import com.spring.cloud.example.product.service.IProductInfoService;
import com.spring.cloud.example.product.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//  Swagger2
@Api(tags = "商品 API")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductInfoService productInfoService;

    @Autowired
    private IProductCategoryService categoryService;

    @ApiOperation(value = "查询所有在架商品，及所属类目")
    @GetMapping("/list")
    public ResultVO<ProductVO> prodList () {
        //  查询所有在架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //  获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //  从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //  构建数据
        List<ProductVO> productVOs = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> ProductInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    ProductInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(ProductInfoVOList);
            productVOs.add(productVO);
        }

        return ResultVOUtil.success(productVOs);
    }

    @ApiOperation(value = "获取商品列表（供订单服务使用）,对外不应该直接暴露数据库实体对象，进行一次封装。ProductInfo -> ProductInfoOutput")
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> productInfoList(@RequestBody List<String> productIdList) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return productInfoService.productInfoList(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "根据购物车信息进行扣库存操作")
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productInfoService.decreaseStock(decreaseStockInputList);
    }
}
