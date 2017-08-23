package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.RecomAdapter;
import com.qingfeng.livesocial.bean.RecommedRespBean;
import com.qingfeng.livesocial.bean.RecommedRespBean.RecommendBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22.
 */

public class RecommendActivity extends BaseActivity {

    @Bind(R.id.gridview)
    GridView gridview;
    @Bind(R.id.btn_jump)
    Button btnJump;
    private List<RecommendBean> datas;

    @Override
    protected int getLayoutById() {
        return R.layout.recommend_layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        getRecommendData();
    }

    @OnClick({R.id.btn_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                gotoActivity(RecommendActivity.this, HomeActivity.class);
                break;
        }
    }

    /**
     * 推荐数据
     */
    private void getRecommendData() {
        RequestParams params = new RequestParams(Urls.DAY_RECOMMEND);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("perfectInfoSucc == " + result);
                RecommedRespBean respon = new Gson().fromJson(result, RecommedRespBean.class);
                if ("y".equals(respon.getMsg())) {
                    datas = respon.getResult();
                    gridview.setAdapter(new RecomAdapter(mContext, datas, imageOptions));
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
