package com.qingfeng.livesocial.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/21.
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.img_phone)
    ImageView imgPhone;
    private final int REQUEST_PHONE_PERMISSIONS = 0;

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
        checkPermission();
    }

    private void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.CAMERA);
            if ((checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.RECORD_AUDIO);
            if ((checkSelfPermission(Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WAKE_LOCK);
            if ((checkSelfPermission(Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
            if (permissionsList.size() != 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PHONE_PERMISSIONS);
            }
        }
    }

    @OnClick({R.id.img_phone, R.id.img_weibo, R.id.img_wechat, R.id.img_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_phone:
                gotoActivity(SplashActivity.this, LoginActivity.class);
                break;
            case R.id.img_weibo:
            case R.id.img_wechat:
            case R.id.img_qq:
                gotoActivity(SplashActivity.this, PerfectInfoActivity.class);
                break;
        }
    }
}
