package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/9/4.
 */

public class PayBean {
    private String mengNum;
    private String mengPrice;

    public PayBean(String mengNum, String mengPrice) {
        this.mengNum = mengNum;
        this.mengPrice = mengPrice;
    }

    public String getMengNum() {
        return mengNum;
    }

    public void setMengNum(String mengNum) {
        this.mengNum = mengNum;
    }

    public String getMengPrice() {
        return mengPrice;
    }

    public void setMengPrice(String mengPrice) {
        this.mengPrice = mengPrice;
    }
}
