package com.zj.springshoppingmall.service;

import com.zj.springshoppingmall.DataTransferObject.ProductQueryParams;
import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.constant.ProductCategory;
import com.zj.springshoppingmall.model.Product;

import java.util.List;

public interface ProductService {
//先想返回值是甚麼，在想要使用甚麼方法才可以呼叫，最後填入所需要的參數

    Integer countProduct(ProductQueryParams productQueryParams);

    //所有商品，為一個List的類型，裡面存放Product數據
    List<Product> getProducts(ProductQueryParams productQueryParams);
    //查詢商品，返回int
    Product getProductById(Integer id);
    //新增商品，返回int
    Integer createProduct(ProductRequest productRequest);
    //更新商品，無返回值
    void updateProduct(Integer productId, ProductRequest productRequest);
    //刪除商品，無返回值
    void deleteProduct(Integer productId);


}
