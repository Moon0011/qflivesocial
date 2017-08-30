package com.qingfeng.livesocial.ui;

import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/29.
 */

public class AnchorAnthenActivity extends BaseActivity {
    @Override
    protected int getLayoutById() {
        return R.layout.anchor_anthen_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_arrow_left, R.id.btn_sumbit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.btn_sumbit:
                gotoActivity(this, AnchorVideoAnthenActivity.class);
                break;
        }
    }
}
