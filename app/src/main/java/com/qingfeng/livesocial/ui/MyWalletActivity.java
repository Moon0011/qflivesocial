package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.MyWalletRespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MyWalletActivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.tv_daycoins)
    TextView tvDaycoins;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_monthcoins)
    TextView tvMonthcoins;
    @Bind(R.id.img_withdraw_cash)
    ImageView imgWithdrawCash;

    @Override
    protected int getLayoutById() {
        return R.layout.my_wallet_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getMyWalletInfo();
    }

    private void getMyWalletInfo() {
        RequestParams params = new RequestParams(Urls.MY_WALLET);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getMyWalletInfo == " + result);
                MyWalletRespBean respBean = new Gson().fromJson(result, MyWalletRespBean.class);
                if (respBean != null && PARAM_Y.equals(respBean.getMsg())) {
                    MyWalletRespBean.MyWalletBean bean = respBean.getResult();
                    if (bean != null) {
                        tvDaycoins.setText(String.valueOf(bean.getDaycoins()));
                        tvMonthcoins.setText(String.valueOf(bean.getMonthcoins()));
                        tvBalance.setText(String.valueOf(bean.getBalance()));
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @OnClick({R.id.img_arrow_left, R.id.img_withdraw_cash, R.id.img_recharge, R.id.img_bill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.img_withdraw_cash:
                gotoActivity(this, WithDrawCashActivity.class);
                break;
            case R.id.img_recharge:
                gotoActivity(this, RechargeActivity.class);
                break;
            case R.id.img_bill:
                gotoActivity(this, BillActivity.class);
                break;
        }
    }

}
