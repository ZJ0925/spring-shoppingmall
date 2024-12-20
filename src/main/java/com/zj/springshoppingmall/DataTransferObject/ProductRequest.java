package com.zj.springshoppingmall.DataTransferObject;

import com.zj.springshoppingmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;


public class ProductRequest {
    //request請求不能為null，只用之前要去maven把spring web複製並把web更改為validation，才可以驗證request
    @NotNull
    //可以由後端生成的變數可以刪去，留下需要由前端輸入的變數即可
    private String productName;
    //如果category是字串的話，就springboot無法得知有甚麼類別，用Enum的可讀性會高一點，更動底層需要檢查rowmapper
    @NotNull
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
