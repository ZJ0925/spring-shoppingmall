package com.zj.springshoppingmall.DataTransferObject;

import jakarta.validation.constraints.NotNull;

//由於BuyItemList要存放json object，所以要新增一個class來對應該json object(產品ID及數量)
public class BuyItem {
    //產品ID
    @NotNull
    private Integer productId;
    //產品購買數量
    @NotNull
    private Integer quantity;

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
}
