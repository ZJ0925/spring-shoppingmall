package com.zj.springshoppingmall.service;

import com.zj.springshoppingmall.DataTransferObject.UserRegisterRequest;
import com.zj.springshoppingmall.model.User;

public interface UserService {

    User getUserById(Integer id);

    Integer register(UserRegisterRequest userRegisterRequest);


}
