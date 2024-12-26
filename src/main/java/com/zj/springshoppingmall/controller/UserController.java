package com.zj.springshoppingmall.controller;

import com.zj.springshoppingmall.DataTransferObject.UserRegisterRequest;
import com.zj.springshoppingmall.model.User;
import com.zj.springshoppingmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("users/register") //註冊帳號算是新增，所以要用POST
    //註冊帳號方法(帳號需要email和密碼，所以帶入參數為email和password)
    //帳號密碼較為隱私，所以需要用requestbody傳遞
    //@RequestBody要接住前端傳進來的body，@Valid是為了要驗證此POST請求的參數
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        //userServicec會提供register的方法，帶入參數為userRegisterRequest
        Integer userId = userService.register(userRegisterRequest);
        //userService提供getUserById的方法，並根據userId的數據去資料庫查詢
        User user = userService.getUserById(userId);
        //回傳ResponseEntity給前端，並且狀態碼為201CREATED，body則是放user的數據
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
