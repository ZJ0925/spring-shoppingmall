package com.zj.springshoppingmall.controller;

import com.zj.springshoppingmall.DataTransferObject.CreatedOrderRequest;
import com.zj.springshoppingmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //@PostMapping內為使用者的->使用者ID->創建的訂單
    //@PathVariable表示從URL路徑中提取參數，並綁定到方法的參數userId。
    //@RequestBody裡要接住CreatedOrderRequest前端傳進來的參數
    //@Valid 表示要驗證POST請求的request body參數
    @PostMapping("/users/{userId}/order")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreatedOrderRequest createdOrderRequest) {
        return null;
    }
}
