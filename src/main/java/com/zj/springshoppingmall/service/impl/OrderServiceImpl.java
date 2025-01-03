package com.zj.springshoppingmall.service.impl;

import com.zj.springshoppingmall.dao.OrderDao;
import com.zj.springshoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
}
