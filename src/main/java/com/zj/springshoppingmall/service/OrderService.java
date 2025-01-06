package com.zj.springshoppingmall.service;

import com.zj.springshoppingmall.DataTransferObject.CreatedOrderRequest;
import com.zj.springshoppingmall.dao.OrderDao;

public interface OrderService {
    Integer createOrder(Integer userId, CreatedOrderRequest createdOrderRequest);
}
