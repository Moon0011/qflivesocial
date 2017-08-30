package com.qingfeng.livesocial.ui.fragment;

import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.qingfeng.livesocial.common.Constants.PARAM_GIFT_SEND_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;

/**
 * Created by Administrator on 2017/8/29.
 */

public class SendGiftFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.my_gift_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {
        sendGift();
    }

    private void sendGift() {
        RequestParams params = new RequestParams(Urls.GIFT);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_GIFT_SEND_TYPE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("sendGift == " + result);
//                MyWalletRespBean respBean = new Gson().fromJson(result, MyWalletRespBean.class);
//                if (respBean != null && PARAM_Y.equals(respBean.getMsg())) {
//                    MyWalletRespBean.MyWalletBean bean = respBean.getResult();
//                    if (bean != null) {
//
//                    }
//                }
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
}
