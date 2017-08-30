package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AnchorInfoRespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.StringUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 */

public class AnchorInfoActivity extends BaseActivity {
    @Bind(R.id.img_head)
    ImageView imgHead;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_qianming)
    TextView tvQianming;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_constell)
    TextView tvConstell;
    @Bind(R.id.btn_label1)
    Button btnLabel1;
    @Bind(R.id.btn_label2)
    Button btnLabel2;
    @Bind(R.id.btn_label3)
    Button btnLabel3;
    private List<Button> labelContainer;

    @Override
    protected int getLayoutById() {
        return R.layout.anchor_data_layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLabel1.setVisibility(View.GONE);
        btnLabel2.setVisibility(View.GONE);
        btnLabel3.setVisibility(View.GONE);
        getAnchorData();
    }

    @Override
    protected void initView() {
        labelContainer = new ArrayList<Button>();
        labelContainer.add(btnLabel1);
        labelContainer.add(btnLabel2);
        labelContainer.add(btnLabel3);
    }

    @Override
    protected void initData() {
        getAnchorData();
    }

    @OnClick({R.id.rl_nickname, R.id.rl_qianming, R.id.rl_labels, R.id.img_arrow_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_nickname:
                gotoActivity(this, NicknameActivity.class);
                break;
            case R.id.rl_qianming:
                gotoActivity(this, QianmingActivity.class);
                break;
            case R.id.rl_labels:
                gotoActivity(this, LabelsActivity.class);
                break;
            case R.id.img_arrow_left:
                finish();
                break;
        }
    }

    private void getAnchorData() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_ANCHOR_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getAnchorData == " + result);
                AnchorInfoRespBean respBean = new Gson().fromJson(result, AnchorInfoRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    AnchorInfoRespBean.AnchorInfoBean bean = respBean.getResult();
                    if (bean != null) {
                        tvNickname.setText(bean.getNickname());
                        tvSex.setText(bean.getSex());
                        tvQianming.setText(bean.getSignature());
                        tvAddress.setText(bean.getAddress());
                        tvBirthday.setText(bean.getBirthday());
                        tvConstell.setText(bean.getConstellation());
                        tvSex.setText(bean.getSex());
                        x.image().bind(imgHead,
                                bean.getAnchorpic(),
                                imageOptions,
                                null);
                        if (!StringUtils.isEmpty(bean.getLabelinfo())) {
                            String[] labelArr = bean.getLabelinfo().split(",");
                            for (int k = 0; k < labelArr.length; k++) {
                                labelContainer.get(k).setVisibility(View.VISIBLE);
                                labelContainer.get(k).setText(labelArr[k]);
                            }
                        }
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
}
