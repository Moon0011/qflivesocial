package com.qingfeng.livesocial.ui;

import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CallSettingActivity extends BaseActivity {
    @Override
    protected int getLayoutById() {
        return R.layout.call_setting_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_arrow, R.id.rl_receive_duration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow:
                finish();
                break;
            case R.id.rl_receive_duration:
                gotoActivity(this, AnswerIntervalActivity.class);
                break;
        }
    }
}
