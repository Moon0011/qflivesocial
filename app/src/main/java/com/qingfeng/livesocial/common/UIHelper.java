package com.qingfeng.livesocial.common;

import android.widget.ImageView;

import com.qingfeng.livesocial.R;

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

    public static void setSexLabel(String sex, ImageView imgView) {
        if ("女".equals(sex)) {
            imgView.setImageResource(R.mipmap.recommend_sex_bg);
        } else if ("男".equals(sex)) {
            imgView.setImageResource(R.mipmap.recommend_sex_bg);
        }
    }
}
