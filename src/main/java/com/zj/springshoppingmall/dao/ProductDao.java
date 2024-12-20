package com.zj.springshoppingmall.dao;

import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);
}
