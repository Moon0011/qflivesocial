package com.qingfeng.livesocial.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.view.Window;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/21.
 */
public class SplashActivity extends BaseActivity {

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
        findViewById(R.id.img_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, TestActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
            }
        });
    }
}
