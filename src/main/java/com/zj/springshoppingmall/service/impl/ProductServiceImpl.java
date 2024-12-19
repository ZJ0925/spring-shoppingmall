package com.zj.springshoppingmall.service.impl;

import com.zj.springshoppingmall.dao.ProductDao;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    //將ProductDao注入就可以由service取得productDao的數據
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
