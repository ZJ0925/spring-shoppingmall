package com.zj.springshoppingmall.dao;

import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();
    //查詢商品
    Product getProductById(Integer id);
    //新增商品
    Integer createProduct(ProductRequest productRequest);
    //更新商品
    void updateProduct(Integer productId, ProductRequest productRequest);
    //刪除商品，刪除商品無返回值
    void deleteProduct(Integer productId);
}
