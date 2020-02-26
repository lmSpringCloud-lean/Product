package com.spring.cloud.example.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {

    @ApiModelProperty(value = "商品Id", example = "157875196366160022")
    @JsonProperty("id")
    private String productId;

    @ApiModelProperty(value = "商品名称", example = "皮蛋粥")
    @JsonProperty("name")
    private String productName;

    @ApiModelProperty(value = "单价", example = "0.01")
    @JsonProperty("price")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品描述", example = "好吃的皮蛋粥")
    @JsonProperty("description")
    private String productDescription;

    @ApiModelProperty(value = "商品小图", example = "//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg")
    @JsonProperty("icon")
    private String productIcon;
}
