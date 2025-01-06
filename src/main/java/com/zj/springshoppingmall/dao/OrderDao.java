package com.zj.springshoppingmall.dao;

import com.zj.springshoppingmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    //創建訂單
    Integer CreateOrder(Integer userId, Integer totalAmount);
    //建立訂單項目表
    void createdOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
