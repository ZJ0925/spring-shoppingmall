package com.zj.springshoppingmall.DataTransferObject;

import jakarta.validation.constraints.NotBlank;

//用來接住使用者傳遞的參數(email和密碼)
public class UserRegisterRequest {
    @NotBlank//不可以為null以外以不可以是空白參數
    private String email;
    @NotBlank
    private String password;

    public @NotBlank String getEmail() {
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
