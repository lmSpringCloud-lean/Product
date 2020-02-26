package com.spring.cloud.example.product.service.impl;

import com.spring.cloud.example.product.common.DecreaseStockInput;
import com.spring.cloud.example.product.common.ProductInfoOutput;
import com.spring.cloud.example.product.constant.Constants;
import com.spring.cloud.example.product.dataobject.ProductInfo;
import com.spring.cloud.example.product.exception.ProductException;
import com.spring.cloud.example.product.repository.ProductInfoRepository;
import com.spring.cloud.example.product.service.IProductInfoService;
import com.spring.cloud.example.product.utils.GsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    @Autowired
    private ProductInfoRepository defaultRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return defaultRepository.findByProductStatus(Constants.ProductStatus.UP.getCode());
    }

    @Override
    public List<ProductInfo> productInfoList(List<String> productIdList) {
        return defaultRepository.findByProductIdIn(productIdList);
    }

    @Override
    public Boolean decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockDB(decreaseStockInputList);

        //将ProductInfoList转换为ProductInfoOutputList
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        // 发送MQ消息
//        ProductInfoOutput productInfoOutput = new ProductInfoOutput();
//        BeanUtils.copyProperties(productInfo, productInfoOutput);
//        amqpTemplate.convertAndSend("productInfo", GsonUtil.objectToJson(productInfoOutput));

        String productInfoMQString = GsonUtil.objectToJson(productInfoOutputList);
        amqpTemplate.convertAndSend("productInfo", GsonUtil.objectToJson(productInfoOutputList));

        return true;
    }

    @Transactional
    List<ProductInfo> decreaseStockDB(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();

        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> optionalProductInfo = defaultRepository.findById(decreaseStockInput.getProductId());
            //  判断商品是否存在
            if (!optionalProductInfo.isPresent()) {
                throw new ProductException(Constants.Result.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = optionalProductInfo.get();
            //  库存是否足够
            Integer productStock = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (productStock < 0) {
                throw new ProductException(Constants.Result.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(productStock);
            defaultRepository.save(productInfo);

            // 此处直接发送MQ消息后续异常回滚，DB不会有问题，但是MQ不会直接回滚

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
