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
}
