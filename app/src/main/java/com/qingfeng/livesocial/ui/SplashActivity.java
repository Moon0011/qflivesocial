package com.qingfeng.livesocial.ui;

import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.ui.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayoutById() {
        return 0;
    }

    @Override
    protected void initView() {
        boolean login = QFApplication.getInstance().login;
        if (login) {
            gotoActivity(this, HomeActivity.class);
        } else {
            gotoActivity(this, StartActivity.class);
        }
    }

    @Override
    protected void initData() {

    }
}
