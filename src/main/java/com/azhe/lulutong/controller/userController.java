package com.azhe.lulutong.controller;

import com.azhe.lulutong.VO.userDao;
import com.azhe.lulutong.VO.verifyCodeDao;
import com.azhe.lulutong.service.userService;
import com.azhe.lulutong.util.sendEmailCodeUtil;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;


@RestController
@RequestMapping("/register")
@Validated
public class userController {
    @Resource
    private userService UserService;

    private HashMap<String, String> verifyMap ;
    // 用户通过邮箱注册
    @PostMapping("/email")
    public ResponseEntity<String> registerUser(@RequestBody userDao registerInfo) {
        String email = registerInfo.getEmail();
        String password = registerInfo.getPassword();
        String userid = registerInfo.getUserId();
        // 调用 UserService 注册用户
        UserService.userRegisterByEmail(email, password, userid);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/sendEmailCode")
    @Parameter(name = "email" ,description = "邮箱")
    public boolean sendEmailCode(@RequestParam("email") String email ){
        email = email.replace("%40","@");
        System.out.println(email);
        verifyMap = sendEmailCodeUtil.sendEmail(email);
        assert verifyMap != null;
        return !verifyMap.isEmpty();
    }

    @PostMapping("/verifyCode")
    public boolean verifyCode(@RequestBody verifyCodeDao verifyCode){
        String email = verifyCode.getEmail();
        String code = verifyCode.getCode();
        return code != null && code.equals(verifyMap.get(email));
    }

    @PostMapping("/loginByEmail")
    public boolean loginByEmail(@RequestBody userDao loginInfo){
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();
        if (password.equals(UserService.selectPasswordByEmail(email))){
            return true;
        }
        else {
        return false;
        }
    }


}
