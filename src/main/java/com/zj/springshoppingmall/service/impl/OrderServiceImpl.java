package com.zj.springshoppingmall.service.impl;

import com.zj.springshoppingmall.DataTransferObject.BuyItem;
import com.zj.springshoppingmall.DataTransferObject.CreatedOrderRequest;
import com.zj.springshoppingmall.dao.OrderDao;
import com.zj.springshoppingmall.dao.ProductDao;
import com.zj.springshoppingmall.dao.UserDao;
import com.zj.springshoppingmall.model.Order;
import com.zj.springshoppingmall.model.OrderItem;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.model.User;
import com.zj.springshoppingmall.service.OrderService;
import com.zj.springshoppingmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

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

        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("{}這個userID不存在",user);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        //迭代 createdOrderRequest 中的所有購買項目 (BuyItem)
        for(BuyItem buyItem : createdOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            //檢查商品是否存在
            if(product == null){
                //如果不存在列印現在購買清單中那一個
                log.warn("{}這個商品不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            //如果資料庫類商品數量小於購買數量
            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("{}這個產品缺貨中",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            //把資料庫內的數量透過set的方式減去購買數量，更新Stock的數量，再呼叫資料庫更新成現在的stock
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

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

    @Override
    public void deleteOrder(Integer orderId) {

        if(orderDao.getOrderById(orderId) == null){
            log.warn("查無此訂單");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        orderDao.deleteOrder(orderId);
        orderDao.deleteOrderItems(orderId);
    }
}
