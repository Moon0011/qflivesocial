package com.qingfeng.livesocial.ui.base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qingfeng.livesocial.common.AppManager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    protected abstract int getLayoutById();

    protected abstract void initView();

    protected abstract void initData();

    protected void gotoActivity(Activity activity, Class nextActivity) {
        startActivity(new Intent(activity, nextActivity),
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (getLayoutById() != 0) {
            setContentView(getLayoutById());
        }
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
