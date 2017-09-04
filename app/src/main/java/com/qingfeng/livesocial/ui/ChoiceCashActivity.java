package com.qingfeng.livesocial.ui;

import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/4.
 */

public class ChoiceCashActivity extends BaseActivity {

    @Override
    protected int getLayoutById() {
        return R.layout.withdraw_cash_layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_arrow_left, R.id.rl_withdraw_cash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.rl_withdraw_cash:
                gotoActivity(this, AddCashTypeActivity.class);
                break;
        }
    }
}
