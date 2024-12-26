package com.zj.springshoppingmall.service.impl;

import com.zj.springshoppingmall.DataTransferObject.UserRegisterRequest;
import com.zj.springshoppingmall.dao.UserDao;
import com.zj.springshoppingmall.model.User;
import com.zj.springshoppingmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊的email(透過user去接住getUserByEmail的值，為String)
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        //如果user不是null的話(表示已經註冊過)
        if(user != null){
            log.warn("該email： {} 已被註冊過",userRegisterRequest.getEmail());
            //拋出Response狀態異常(BAD_REQUEST， HttpStatus = 400)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }


}
