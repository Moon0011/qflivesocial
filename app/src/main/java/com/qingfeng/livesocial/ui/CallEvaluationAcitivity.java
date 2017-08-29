package com.qingfeng.livesocial.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CallEvaluationAcitivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.ll_evaluation_detail)
    LinearLayout llEvaluationDetail;
    private String uid;

    @Override
    protected int getLayoutById() {
        return R.layout.call_evaluation_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        uid = getIntent().getExtras().getString(PARAM_UID);
        getCallEvaluation(uid);
    }

    private void getCallEvaluation(String uid) {
        RequestParams params = new RequestParams(Urls.CALL_EVALUATION);
        params.addParameter(PARAM_UID, uid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getCallEvaluation == " + result);
//                CallEvaluationRespBean respBean = new Gson().fromJson(result, CallEvaluationRespBean.class);
//                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
//
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

    @OnClick({R.id.img_arrow_left, R.id.ll_evaluation_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.ll_evaluation_detail:
                Bundle b = new Bundle();
                b.putString(PARAM_UID, uid);
                gotoActivityWithBundle(CallEvaluationAcitivity.this, EvaluationDetailAcitivity.class, b);
                break;
        }
    }
}
