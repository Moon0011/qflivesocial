package com.qingfeng.livesocial.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/23.
 */

public class UserInfoBean implements Serializable {
    private int uid;
    private String username;
    private String sign;
    private int islogin;

    public UserInfoBean() {
    }

    public UserInfoBean(int uid, String username, String sign, int islogin) {
        this.uid = uid;
        this.username = username;
        this.sign = sign;
        this.islogin = islogin;
    }

    public int getIslogin() {
        return islogin;
    }

    public void setIslogin(int islogin) {
        this.islogin = islogin;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
