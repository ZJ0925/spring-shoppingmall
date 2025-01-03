package com.zj.springshoppingmall.DataTransferObject;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

//DataTransferObject的package存放前端傳進來的參數
public class CreatedOrderRequest {
    //4-4結構化的呈現數據
    @NotEmpty //驗證List或Map不可以為空，必須要友直存在
    private List<BuyItem> buyItemList;

    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
