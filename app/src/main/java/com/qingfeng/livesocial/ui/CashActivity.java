package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.RelativeLayout;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/4.
 */

public class CashActivity extends BaseActivity {
    @Bind(R.id.rl_wechatpay)
    RelativeLayout rlWechatpay;
    @Bind(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    private boolean isWechat = true;

    @Override
    protected int getLayoutById() {
        return R.layout.cash_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_arrow_left, R.id.tv_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.tv_switch:
                if (isWechat) {
                    rlAlipay.setVisibility(View.VISIBLE);
                    rlWechatpay.setVisibility(View.GONE);
                } else {
                    rlAlipay.setVisibility(View.GONE);
                    rlWechatpay.setVisibility(View.VISIBLE);
                }
                isWechat = !isWechat;
                break;
        }
    }
}
