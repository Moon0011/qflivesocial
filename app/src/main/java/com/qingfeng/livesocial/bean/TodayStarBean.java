package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/22.
 */

public class TodayStarBean {
    private String name;
    private String sex;
    private int age;
    private String imgUrl;

    public TodayStarBean(String name, String sex, int age, String imgUrl) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
