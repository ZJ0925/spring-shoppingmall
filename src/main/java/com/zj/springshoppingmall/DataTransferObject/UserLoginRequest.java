package com.zj.springshoppingmall.DataTransferObject;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//DataTransferObject的package存放前端傳進來的參數
public class UserLoginRequest {
    @NotBlank//不可以為null以外以不可以是空白參數
    @Email //當前端傳不是email格式的字串的話，就會顯示錯誤
    public String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
