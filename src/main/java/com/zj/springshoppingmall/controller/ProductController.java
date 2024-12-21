package com.zj.springshoppingmall.controller;

import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.web.bind.annotation.*;

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
    //新增商品的方法，從ProductRequest驗證參數不可以為null
    //@RequestBody表示要從前端接住傳過來的json參數
    //一定要加上@Valid這樣request上的@NotNull才會生效
    @PostMapping("/products")
    public ResponseEntity<Product> creatProduct(@RequestBody @Valid ProductRequest productRequest){
        //此方法會創建商品出來
        Integer productId = productService.createProduct(productRequest);
        //從資料庫取得商品的數據
        Product product = productService.getProductById(productId);
        //回傳response狀態碼，body則是porduct數據
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    /*1.從HTTP請求URL的路徑中提取產品productId
    @PathVariable表示從URL路徑中提取一個參數，並綁定到方法的參數productId。
    @RequestBody表示要從前端接住傳過來的json參數
    一定要加上@Valid這樣request上的@NotNull才會生效
     */

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        //先使用productId檢查是否存在
        Product product = productService.getProductById(productId);
        //如果找不到產品
        if(product == null){
            //就顯示Response狀態404，並建立
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改商品數據(productService會提供一個updateProduct的方法，參數為productId以及productRequest)
        productService.updateProduct(productId, productRequest);
        //顯示更新後的商品數據(上面商品修改方法成功後，就可以使用productId查詢更新後的商品)
        Product updatedProduct = productService.getProductById(productId);
        //回傳ResponseEntity狀態碼，body則是更新後的商品數據
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
}
