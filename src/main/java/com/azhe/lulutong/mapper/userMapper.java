package com.azhe.lulutong.mapper;

import com.azhe.lulutong.VO.userDao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper extends BaseMapper<userDao> {
    // 用户通过邮箱注册
    @Insert("INSERT INTO user_info(email, password, user_id) VALUES(#{email}, #{password}, #{userId})")
    void registerUserInfoByEmail(@Param("email") String email, @Param("password") String password, @Param("userId") String userId);

    //根据邮箱查询密码
    @Select("SELECT password FROM user_info WHERE email = #{email}")
    String selectPasswordByEmail(@Param("email") String email);


}
