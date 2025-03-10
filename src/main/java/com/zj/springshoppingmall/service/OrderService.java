package com.zj.springshoppingmall.service;

import com.zj.springshoppingmall.DataTransferObject.CreatedOrderRequest;
import com.zj.springshoppingmall.dao.OrderDao;
import com.zj.springshoppingmall.model.Order;

public interface OrderService {
    //取得訂單資訊
    Order getOrderById(Integer orderId);
    //創建訂單
    Integer createOrder(Integer userId, CreatedOrderRequest createdOrderRequest);
    //取消訂單(刪除商品不用返回值)
    void deleteOrder(Integer orderId);


}
