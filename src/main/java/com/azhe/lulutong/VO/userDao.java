package com.azhe.lulutong.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户信息表
 *
 * @author Azhe
 */

@TableName("user_info")
public class userDao {

    @TableId
    private String id;

    private String userId;

    private String email;

    private String password;


    private String faceUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public userDao(String userId, String id, String email, String password, String faceUrl) {
        this.userId = userId;
        this.id = id;
        this.email = email;
        this.password = password;
        this.faceUrl = faceUrl;
    }


    public userDao() {

    }


}
