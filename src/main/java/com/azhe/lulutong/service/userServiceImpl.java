package com.azhe.lulutong.service;

import com.azhe.lulutong.mapper.userMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


@Service
@Validated
public class userServiceImpl implements userService {

    @Resource
    private userMapper UserMapper;

    @Override
    public void userRegisterByEmail(String email, String password, String userId) {
        UserMapper.registerUserInfoByEmail(email, password, userId);
    }

    @Override
    public String selectPasswordByEmail(String email) {
        return UserMapper.selectPasswordByEmail(email);
    }
}
