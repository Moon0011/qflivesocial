package com.qingfeng.livesocial.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/23.
 */

public class UserInfoBean implements Serializable {
    private int uid;
    private String username;
    private String token;
    private String sign;
    private int islogin;
    private int curroomnum;

    public UserInfoBean() {
    }

    public UserInfoBean(int uid, String username, String sign, int islogin, int curroomnum, String token) {
        this.uid = uid;
        this.username = username;
        this.sign = sign;
        this.islogin = islogin;
        this.curroomnum = curroomnum;
        this.token = token;
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

    public int getCurroomnum() {
        return curroomnum;
    }

    public void setCurroomnum(int curroomnum) {
        this.curroomnum = curroomnum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
