package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.LabelAdapter;
import com.qingfeng.livesocial.bean.LabelRespBean;
import com.qingfeng.livesocial.bean.RespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_A;
import static com.qingfeng.livesocial.common.Constants.PARAM_LABEL;
import static com.qingfeng.livesocial.common.Constants.PARAM_LABEL_FOR_PERSONAL_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_LABEL_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/30.
 */

public class LabelsActivity extends BaseActivity {
    @Bind(R.id.label_gridview)
    GridView labelGridview;
    @Bind(R.id.btn_select_label1)
    Button btnSelectLabel1;
    @Bind(R.id.btn_select_label2)
    Button btnSelectLabel2;
    @Bind(R.id.btn_select_label3)
    Button btnSelectLabel3;

    private List<LabelRespBean.LabelBean> selectLabels = new ArrayList<>();
    private List<LabelRespBean.LabelBean> labels;
    private List<Button> btnLabels = new ArrayList<>();

    @Override
    protected int getLayoutById() {
        return R.layout.labels_layout;
    }

    @Override
    protected void initView() {
        labelGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectLabels.size() <= 2) {
                    selectLabels.add(labels.get(position));
                    for (int i = 0; i < selectLabels.size(); i++) {
                        LabelRespBean.LabelBean bean = selectLabels.get(i);
                        btnLabels.get(i).setText(bean.getContent());
                        btnLabels.get(i).setTextColor(getResources().getColor(R.color.white));
                        btnLabels.get(i).setBackgroundResource(R.color.label_selected_color);
                    }
                    ImageView imgLabel = (ImageView) view.findViewById(R.id.img_label);
                    TextView tvLable = (TextView) view.findViewById(R.id.tv_label);
                    imgLabel.setBackgroundResource(R.color.label_selected_color);
                    tvLable.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
    }

    @Override
    protected void initData() {
        btnLabels.add(btnSelectLabel1);
        btnLabels.add(btnSelectLabel2);
        btnLabels.add(btnSelectLabel3);
        getLabels();
    }

    @OnClick({R.id.img_arrow_left, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.btn_save:
                updateLabels();
                break;
        }
    }

    /**
     * 获取标签
     */
    private void getLabels() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_ANCHOR_INFO_GET_LABELS);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_LABEL_FOR_PERSONAL_TYPE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getLabels == " + result);
                LabelRespBean respBean = new Gson().fromJson(result, LabelRespBean.class);
                if (respBean != null && PARAM_Y.equals(respBean.getMsg())) {
                    labels = respBean.getResult();
                    LabelAdapter adapter = new LabelAdapter(mContext, labels);
                    labelGridview.setAdapter(adapter);
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

    /**
     * 更新标签
     */
    private void updateLabels() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_LABEL_INFO_EDIT_TYPE_VALUE);
        StringBuffer sbf = new StringBuffer();
        for (LabelRespBean.LabelBean bean : selectLabels) {
            sbf.append(bean.getContent() + ",");
        }
        String labels = sbf.toString().substring(0, sbf.toString().length() - 1);
        params.addParameter(PARAM_LABEL, labels);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateLabels == " + result);
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
