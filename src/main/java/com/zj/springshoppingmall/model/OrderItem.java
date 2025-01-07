package com.zj.springshoppingmall.model;

public class OrderItem {
    private Integer oderitemId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer amount;

    private String productName;
    private String imageUrl;

    public Integer getOderitemId() {
        return oderitemId;
    }

    public void setOderitemId(Integer oderitemId) {
        this.oderitemId = oderitemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
