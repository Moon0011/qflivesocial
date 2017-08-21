package com.qingfeng.livesocial.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qingfeng.livesocial.common.AppManager;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutById();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutById() != 0) {
            setContentView(getLayoutById());
        }
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
