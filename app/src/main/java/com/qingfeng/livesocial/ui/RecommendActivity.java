package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.RecomAdapter;
import com.qingfeng.livesocial.bean.TodayStarBean;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import java.util.ArrayList;
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
    private List<TodayStarBean> datas;

    @Override
    protected int getLayoutById() {
        return R.layout.recommend_layout;
    }

    @Override
    protected void initView() {
        datas = new ArrayList<>();
        datas.add(new TodayStarBean("阿夕", "f", 23, ""));
        datas.add(new TodayStarBean("露露", "f", 22, ""));
        datas.add(new TodayStarBean("P子", "f", 21, ""));
        datas.add(new TodayStarBean("桃曦", "f", 23, ""));
        datas.add(new TodayStarBean("桃曦", "f", 25, ""));
        datas.add(new TodayStarBean("桃曦", "f", 20, ""));
    }

    @Override
    protected void initData() {
        RecomAdapter adapter = new RecomAdapter(mContext, datas);
        gridview.setAdapter(adapter);
    }

    @OnClick({R.id.btn_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                gotoActivity(RecommendActivity.this, HomeActivity.class);
                break;
        }
    }
}
