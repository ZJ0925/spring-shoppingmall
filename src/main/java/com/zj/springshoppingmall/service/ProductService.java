package com.zj.springshoppingmall.service;

import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.model.Product;

public interface ProductService {
    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);
}
