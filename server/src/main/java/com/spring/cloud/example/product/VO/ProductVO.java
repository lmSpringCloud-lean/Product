package com.spring.cloud.example.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class ProductVO {
    //
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "String", value = "类目名称", example = "热榜")
    private String categoryName;
    //
    @JsonProperty("type")
    @ApiModelProperty(name = "type", value = "类目编号", example = "1")
    private Integer categoryType;
    //
    @JsonProperty("foods")
    @ApiModelProperty("商品列表")
    private List<ProductInfoVO> productInfoVOList;
}
