package com.zj.springshoppingmall.controller;

import com.zj.springshoppingmall.DataTransferObject.ProductQueryParams;
import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.constant.ProductCategory;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//表示為一個Controller
@RestController
public class ProductController {
    //controller與service溝通
    @Autowired
    private ProductService productService;

    //List裝著product的數據，不管List有無數據，都回傳200狀態碼給前端，單一個才需要做判斷數據有無存在
    @GetMapping("products")
    public ResponseEntity<List< Product>> getProducts(
            // @RequestParam可以將 HTTP 請求的參數綁定到方法參數上，代表可以將分類的關鍵字連到URL上，required(必須的) = false，表示不一定需要
           @RequestParam(required = false) ProductCategory category,
           @RequestParam(required = false) String search

    ){
        ProductQueryParams productqueryparams = new ProductQueryParams();
        productqueryparams.setCategory(category);
        productqueryparams.setSearch(search);
        //無任何參數，且會回傳一個商品的List回來
        List<Product> productsList = productService.getProducts(productqueryparams);
        //回傳ResponseEntity狀態OK，且body填入products的List
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

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
    //返回值為ResponseEntity，@PathVariable Integer productId表示productId的值是從URL路徑傳進來
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        //productService會有一個叫deleteProductById的方法，傳入值為productId
        productService.deleteProduct(productId);
        //回傳ResponseEntity的狀態為NO_CONTENT(204)表示已經被刪除
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
