package com.qingfeng.livesocial.common;

/**
 * Created by Administrator on 2017/8/23.
 */

public class Urls {
    public static final String DOMAIN_NAME = "video.520cai.cn";
    public static final String VERIFY = String.format("http://%s/index.php/api/login/verify", DOMAIN_NAME);
    public static final String LOGIN = String.format("http://%s/index.php/api/login/dologin", DOMAIN_NAME);
    public static final String PERFECT_INFO = String.format("http://%s/index.php/api/user/perfectinfo", DOMAIN_NAME);
    public static final String DAY_RECOMMEND = String.format("http://%s/index.php/api/user/dayrecommend", DOMAIN_NAME);
    public static final String RECOMMEND = String.format("http://%s/index.php/api/user/recommend", DOMAIN_NAME);
    public static final String SLIDE = String.format("http://%s/index.php/api/user/slide", DOMAIN_NAME);
    public static final String RANKLIST = String.format("http://%s/index.php/api/user/ranklist", DOMAIN_NAME);
    public static final String WEBSITE = String.format("http://%s/index.php/api/user/website", DOMAIN_NAME);
    public static final String CALL_EVALUATION = String.format("http://%s/index.php/api/user/callevaluation", DOMAIN_NAME);
    public static final String PERSONAL_CENTER = String.format("http://%s/index.php/api/user/usercenter", DOMAIN_NAME);//个人中心
    public static final String PERSONAL_CENTER_EDIT_INFO = String.format("http://%s/index.php/api/user/editinfo", DOMAIN_NAME);//个人中心-编辑资料
    public static final String PERSONAL_CENTER_ANCHOR_INFO = String.format("http://%s/index.php/api/user/userinfo", DOMAIN_NAME);//个人中心-主播信息
    public static final String PERSONAL_CENTER_ANCHOR_INFO_GET_LABELS = String.format("http://%s/index.php/api/user/label", DOMAIN_NAME);//获取标签
    public static final String MY_WALLET = String.format("http://%s/index.php/api/user/account", DOMAIN_NAME);//我的账户
    public static final String GIFT = String.format("http://%s/index.php/api/user/gift", DOMAIN_NAME);//礼物页
    public static final String SEND_GIFT_LIST = String.format("http://%s/index.php/api/user/giftlists", DOMAIN_NAME);//送出礼物页
    public static final String ATTENTION = String.format("http://%s/index.php/api/user/attpage", DOMAIN_NAME);//关注页
    public static final String DELETE_PHOTO = String.format("http://%s/index.php/api/user/delphoto", DOMAIN_NAME);//删除图片
}
