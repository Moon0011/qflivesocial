package com.qingfeng.livesocial.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.RecommedRespBean;
import com.qingfeng.livesocial.bean.RecommedRespBean.RecommendBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.widget.RoundedImageView;

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

    //    @Bind(R.id.gridview)
//    GridView gridview;
    @Bind(R.id.btn_jump)
    Button btnJump;
    @Bind(R.id.ll_container)
    LinearLayout ll_container;
    private List<RecommendBean> datas;
    private LayoutInflater mInflater;

    @Override
    protected int getLayoutById() {
        return R.layout.recommend_layout;
    }

    @Override
    protected void initView() {
        mInflater = LayoutInflater.from(mContext);
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
//                    gridview.setAdapter(new RecomAdapter(mContext, datas, imageOptions));
                    int size = datas.size() % 2 == 0 ? datas.size() / 2 : datas.size() / 2 + 1;
                    for (int i = 0; i < size; i++) {
                        LinearLayout ll = (LinearLayout) mInflater.inflate(R.layout.recommend_item_layout, null);
                        for (int j = 0; j < datas.size(); j += 2) {
                            TextView nickName = (TextView) ll.findViewById(R.id.tv_name);
                            TextView nickName2 = (TextView) ll.findViewById(R.id.tv_name2);
                            TextView age = (TextView) ll.findViewById(R.id.tv_age);
                            TextView age2 = (TextView) ll.findViewById(R.id.tv_age2);
                            RoundedImageView imageHead = (RoundedImageView) ll.findViewById(R.id.img_head);
                            RoundedImageView imageHead2 = (RoundedImageView) ll.findViewById(R.id.img_head2);
                            if (datas.get(j) != null) {
                                nickName.setText(datas.get(j).getNickname());
                                age.setText(datas.get(j).getAge());
                                x.image().bind(imageHead,
                                        datas.get(j).getAnchorpic(),
                                        imageOptions,
                                        null);
                            }
                            if (datas.get(j + 1) != null) {
                                nickName2.setText(datas.get(j + 1).getNickname());
                                age2.setText(datas.get(j + 1).getAge());
                                x.image().bind(imageHead2,
                                        datas.get(j + 1).getAnchorpic(),
                                        imageOptions,
                                        null);
                            }
                        }
                        ll_container.addView(ll);
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
