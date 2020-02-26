package com.spring.cloud.example.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
//  类目表
public class ProductCategory {
    @Id
    private Integer categoryId;
    //  类目名字
    private String categoryName;
    //  类目编号
    private Integer categoryType;
    //  创建时间
    private Date createTime;
    //  修改时间
    private Date updateTime;
}
