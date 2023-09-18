package com.azhe.lulutong.service;

public interface userService {

    void  userRegisterByEmail(String email,String password, String  userId);
    String selectPasswordByEmail(String email);
}
