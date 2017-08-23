package com.qingfeng.livesocial.common;

/**
 * Created by Administrator on 2017/8/23.
 */

public class UIHelper {

    public static String verifyPhone(String type) {
        if ("a".equals(type)) {
            return "手机号码不正确或为空";
        } else if ("b".equals(type)) {
            return "验证码过期";
        } else if ("y".equals(type)) {
            return "验证码发送成功";
        } else {
            return "未知";
        }
    }
}
