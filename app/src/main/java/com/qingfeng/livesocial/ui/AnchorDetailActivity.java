package com.qingfeng.livesocial.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AnchorDetailRespBean;
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
 * Created by Administrator on 2017/8/28.
 */

public class AnchorDetailActivity extends BaseActivity {

    @Bind(R.id.video_bg_img)
    ImageView videoImg;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.rl_top_head)
    RelativeLayout rlTopHead;
    @Bind(R.id.img_video_player)
    ImageView imgVideoPlayer;
    @Bind(R.id.btn_label11)
    Button btnLabel11;
    @Bind(R.id.btn_label12)
    Button btnLabel12;
    @Bind(R.id.btn_label13)
    Button btnLabel13;
    @Bind(R.id.ll_labels1)
    LinearLayout llLabels1;
    @Bind(R.id.tv_label)
    TextView tvLabel;
    @Bind(R.id.ll_gift_container)
    LinearLayout llGiftContainer;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;

    @Override
    protected int getLayoutById() {
        return R.layout.anchor_detail_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle b = getIntent().getExtras();
        String uid = (String) b.get(PARAM_UID);
        getAnchorDetail(uid);
    }

    private void getAnchorDetail(String uid) {
        RequestParams params = new RequestParams(Urls.WEBSITE);
        params.addParameter(PARAM_UID, uid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getAnchorDetail == " + result);
                AnchorDetailRespBean respBean = new Gson().fromJson(result, AnchorDetailRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    AnchorDetailRespBean.AnchorDetailBean bean = respBean.getResult();
                    tvNickname.setText(bean.getNickname());
                    x.image().bind(videoImg,
                            bean.getVideo(),
                            imageOptions,
                            null);
                    tvIntroduce.setText(bean.getSex() + " " + bean.getAge() + " " + bean.getConstellation() + " " + bean.getAddress());
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


    @OnClick({R.id.img_arrow_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
        }
    }
}