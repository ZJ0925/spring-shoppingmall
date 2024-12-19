package com.zj.springshoppingmall.controller;

import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//表示為一個Controller
@RestController
public class ProductController {
    //controller與service溝通
    @Autowired
    private ProductService productService;


    //取得model這個package的Product這個class的數據
    // @GetMapping表示要取得的是某一筆商品的數據
    /*
    1.從HTTP請求URL的路徑中提取產品productId
    @PathVariable表示從URL路徑中提取一個參數，並綁定到方法的參數productId。
    EX:http://localhost:8080/producs/123
    也就是當前端請求URL路徑時，就透過productService的getProductById方法
    去資料庫中查詢資料出來(因為service會call productDao，productDao再從資料提取出來)
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        //由controller方從service提取ID
        Product product = productService.getProductById(productId);
        if(product != null){ //如果product不是空值
            //回傳Http狀態OK(200)，body填上product數據
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            //回傳找不到狀態為(404)，並且body為空值
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
