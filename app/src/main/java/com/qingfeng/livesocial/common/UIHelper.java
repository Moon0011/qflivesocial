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

    public static String setSex(String sex) {
        if ("f".equals(sex)) {
            return "女";
        } else if ("m".equals(sex)) {
            return "男";
        }
        return "";
    }

    public static String setSex2(String sex) {
        if ("女".equals(sex)) {
            return "f";
        } else if ("男".equals(sex)) {
            return "m";
        }
        return "";
    }

    public static String setAttention(int attstatus) {
        if (attstatus == 1) {
            return "已关注";
        } else if (attstatus == 0) {
            return "未关注";
        }
        return "";
    }

}
