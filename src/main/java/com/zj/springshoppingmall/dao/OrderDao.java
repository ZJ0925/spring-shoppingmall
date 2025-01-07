package com.zj.springshoppingmall.dao;

import com.zj.springshoppingmall.model.Order;
import com.zj.springshoppingmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    //訂單項目會有多項返回值，所以返回值為List
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    //創建訂單
    Integer CreateOrder(Integer userId, Integer totalAmount);
    //建立訂單項目表
    void createdOrderItems(Integer orderId, List<OrderItem> orderItemList);


}
