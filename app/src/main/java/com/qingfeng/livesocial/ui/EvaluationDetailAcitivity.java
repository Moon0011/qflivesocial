package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;

/**
 * Created by Administrator on 2017/8/29.
 */

public class EvaluationDetailAcitivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_signature)
    TextView tvSignature;
    private String uid;

    @Override
    protected int getLayoutById() {
        return R.layout.evaluation_detail_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        uid = getIntent().getExtras().getString(PARAM_UID);
    }

    @OnClick({R.id.img_arrow_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
        }
    }
}
