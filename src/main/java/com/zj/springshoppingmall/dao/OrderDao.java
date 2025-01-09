package com.zj.springshoppingmall.dao;

import com.zj.springshoppingmall.model.Order;
import com.zj.springshoppingmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);
    Order getOrderByUserId(Integer userId);

    //訂單項目會有多項返回值，所以返回值為List
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    //創建訂單
    Integer CreateOrder(Integer userId, Integer totalAmount);
    //建立訂單項目表
    void createdOrderItems(Integer orderId, List<OrderItem> orderItemList);
    //取消訂單
    void deleteOrder(Integer orderId);
    //刪除訂單項目
    void deleteOrderItems(Integer orderId);


}
