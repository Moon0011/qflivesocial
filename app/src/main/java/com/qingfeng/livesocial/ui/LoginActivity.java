package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.LoginRespBean;
import com.qingfeng.livesocial.bean.RespBean;
import com.qingfeng.livesocial.bean.UserInfoBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_PHONE;
import static com.qingfeng.livesocial.common.Constants.PARAM_VERIFY_CODE;
import static com.qingfeng.livesocial.common.Constants.VERIFY_CODE_TEST;
import static com.qingfeng.livesocial.common.UIHelper.verifyPhone;

/**
 * Created by Administrator on 2017/8/22.
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.btn_phone_login)
    Button btnPhoneLogin;
    @Bind(R.id.tv_getVerifyCode)
    TextView tvGetVerifyCode;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;


    @Override
    protected int getLayoutById() {
        return R.layout.phone_login_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_arrow_left, R.id.btn_phone_login, R.id.tv_getVerifyCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.btn_phone_login://登陆
                showProgress();
                login(etPhoneNum.getText().toString().trim(), VERIFY_CODE_TEST);
                break;
            case R.id.tv_getVerifyCode://获取验证码
                showProgress();
                String phone = etPhoneNum.getText().toString().trim();
                getVerifyCode(phone);
                break;
        }
    }

    /**
     * 登陆
     */
    private void login(String phoneNo, String verifyCode) {
        RequestParams params = new RequestParams(Urls.LOGIN);
        params.addQueryStringParameter(PARAM_PHONE, phoneNo);
        params.addQueryStringParameter(PARAM_VERIFY_CODE, verifyCode);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("login == " + result);
                LoginRespBean loginRespBean = new Gson().fromJson(result, LoginRespBean.class);
                if ("a".equals(loginRespBean.getMsg())) {
                    showToast("验证码不匹配");
                    dismissProgress();
                    return;
                }
                int uid = loginRespBean.getResult().getUid();
                String username = loginRespBean.getResult().getUsername();
                String sign = loginRespBean.getResult().getSign();
                int islogin = loginRespBean.getResult().getIslogin();
                UserInfoBean userInfoBean = new UserInfoBean(uid, username, sign, islogin);
                QFApplication.getInstance().saveUserInfo(userInfoBean);
                if (islogin == 0) {
                    gotoActivity(LoginActivity.this, PerfectInfoActivity.class);
                } else {
                    gotoActivity(LoginActivity.this, RecommendActivity.class);
                }
                showToast("登陆成功");
                dismissProgress();
                LogUtil.e("login: " + "uid = " + uid + " , username =" + username + " , sign =" + sign);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                dismissProgress();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                dismissProgress();
            }

            @Override
            public void onFinished() {
                dismissProgress();
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getVerifyCode(String phoneNo) {
        RequestParams params = new RequestParams(Urls.VERIFY);
        params.addQueryStringParameter(PARAM_PHONE, phoneNo);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getVerifyCode == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                showToast(verifyPhone(respBean.getMsg()));
                dismissProgress();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                dismissProgress();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                dismissProgress();
            }

            @Override
            public void onFinished() {
                dismissProgress();
            }
        });
    }

}
