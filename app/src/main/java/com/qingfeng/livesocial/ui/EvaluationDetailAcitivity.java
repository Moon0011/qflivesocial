package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.CallEvaluationRespBean.ResultBean.CommentinfoBean;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.TimeUtils;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.R.id.tv_signature;

/**
 * Created by Administrator on 2017/8/29.
 */

public class EvaluationDetailAcitivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(tv_signature)
    TextView tvSignature;
    @Bind(R.id.tv_createtime)
    TextView tvCreatetime;
    private CommentinfoBean commentinfoBean;

    @Override
    protected int getLayoutById() {
        return R.layout.evaluation_detail_layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        commentinfoBean = (CommentinfoBean) getIntent().getExtras().getSerializable("commedbean");
        if (null != commentinfoBean) {
            tvNickname.setText(commentinfoBean.getNickname());
            tvSignature.setText(commentinfoBean.getContent());
            tvCreatetime.setText(TimeUtils.getStrTime(commentinfoBean.getCreattime(), "yyyy/MM/dd hh:mm:ss"));
        }
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
