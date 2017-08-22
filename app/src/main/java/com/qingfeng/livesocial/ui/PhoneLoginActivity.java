package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PhoneLoginActivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.btn_phone_login)
    Button btnPhoneLogin;

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

    @OnClick({R.id.img_arrow_left, R.id.btn_phone_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.btn_phone_login:
                gotoActivity(PhoneLoginActivity.this, RegistInfoActivity.class);
                break;
        }
    }
}
