package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.RespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_A;
import static com.qingfeng.livesocial.common.Constants.PARAM_NICKNAME;
import static com.qingfeng.livesocial.common.Constants.PARAM_NICKNAME_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/30.
 */

public class NicknameActivity extends BaseActivity {
    @Bind(R.id.et_nickname)
    EditText etNickname;

    @Override
    protected int getLayoutById() {
        return R.layout.nickname_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_arrow_left, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.btn_save:
                updateNickName();
                break;
        }
    }

    private void updateNickName() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_NICKNAME_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_NICKNAME, etNickname.getText().toString().trim());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateNickName == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("修改成功");
                } else if (PARAM_A.equals(respBean.getMsg())) {
                    showToast("不能为空");
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
}
