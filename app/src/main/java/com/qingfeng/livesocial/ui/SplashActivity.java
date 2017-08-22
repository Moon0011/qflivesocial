package com.qingfeng.livesocial.ui;

import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/21.
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.img_phone)
    ImageView imgPhone;

    @Override
    protected int getLayoutById() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.splash_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.img_phone, R.id.img_weibo, R.id.img_wechat, R.id.img_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_phone:
                gotoActivity(SplashActivity.this, PhoneLoginActivity.class);
                break;
            case R.id.img_weibo:
            case R.id.img_wechat:
            case R.id.img_qq:
                gotoActivity(SplashActivity.this, RegistInfoActivity.class);
                break;
        }
    }
}
