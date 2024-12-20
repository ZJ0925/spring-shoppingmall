package com.zj.springshoppingmall.model;

import com.zj.springshoppingmall.constant.ProductCategory;

import java.util.Date;

public class Product {
    //宣告商品各個數據
   private Integer productId;
   private String productName;
   //如果category是字串的話，就spingboot無法得知有甚麼類別，用Enum的可讀性會高一點，更動底層需要檢查rowmapper
   private ProductCategory category;
   private String imageUrl;
   private Integer price;
   private Integer stock;
   private String description;
   private Date createdDate;
   private Date lastModifiedDate;

   //宣告個個數據的getter與setter
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

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

    public ProductCategory setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return null;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
