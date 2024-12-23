package com.zj.springshoppingmall.service;

import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.model.Product;

public interface ProductService {
    //查詢商品，返回int
    Product getProductById(Integer id);
    //新增商品，返回int
    Integer createProduct(ProductRequest productRequest);
    //更新商品，無返回值
    void updateProduct(Integer productId, ProductRequest productRequest);
    //刪除商品，無返回值
    void deleteProduct(Integer productId);
}
