package com.zj.springshoppingmall.service.impl;

import com.zj.springshoppingmall.DataTransferObject.BuyItem;
import com.zj.springshoppingmall.DataTransferObject.CreatedOrderRequest;
import com.zj.springshoppingmall.dao.OrderDao;
import com.zj.springshoppingmall.dao.ProductDao;
import com.zj.springshoppingmall.model.Order;
import com.zj.springshoppingmall.model.OrderItem;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        //Order類型並存在orderDao這個class裡的getOrderById的方法
        //取得order table中的數據
        Order order = orderDao.getOrderById(orderId);
        //取得orderItem中的數據
        List<OrderItem> OrderItemList = orderDao.getOrderItemsByOrderId(orderId);
        //將order的class內加入OrderItemList後再回傳order，讓order裡的訂單及訂單清單都回傳給前端
        order.setOrderItemList(OrderItemList);
        return order;
    }

    /*
        若在同一個class中需要同時使用多個資料庫就需要使用(如同交易Transactional)，使
        資料庫同時成功執行，萬一任一執行失敗，則會復原已經執行過的資料庫操作
         */
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreatedOrderRequest createdOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        //迭代 createdOrderRequest 中的所有購買項目 (BuyItem)
        for(BuyItem buyItem : createdOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            //計算此購買此數量(buyItem.getQuantity)的商品(product.getPrice)
            //需要多少錢(amount)
            int amount = buyItem.getQuantity() * product.getPrice();
            //最後加總
            totalAmount += amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            //將orderItem裡的productId設置為購物清單裡的ProductId
            orderItem.setProductId(buyItem.getProductId());
            //將orderItem裡的Quantity設置為購物清單裡的Quantity
            orderItem.setQuantity(buyItem.getQuantity());
            //將orderItem裡的Amount設置為迴圈計算出來的amount(商品數量*商品價錢)
            orderItem.setAmount(amount);
            //將設置好的orderItem加入到新建立的orderItemList
            orderItemList.add(orderItem);

        }

        //先從order的table創建訂單出來
        Integer orderId = orderDao.CreateOrder(userId, totalAmount);
        //在於orderItem的table創建數據出來
        orderDao.createdOrderItems(orderId, orderItemList);
        return orderId;
    }
}
