package com.qingfeng.livesocial.ui;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MFragmentPagerAdapter;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.DayRanklistFragment;
import com.qingfeng.livesocial.ui.fragment.TotalRanklistFragment;
import com.qingfeng.livesocial.ui.fragment.WeekRanklistFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.DAY_RANKLIST_NAME;
import static com.qingfeng.livesocial.common.Constants.TOTAL_RANKLIST_NAME;
import static com.qingfeng.livesocial.common.Constants.WEEK_RANKLIST_NAME;

/**
 * Created by Administrator on 2017/8/25.
 */
public class RanklistActivity extends BaseActivity {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpage)
    ViewPager tabViewpage;
    private String titleArr[] = {DAY_RANKLIST_NAME, WEEK_RANKLIST_NAME, TOTAL_RANKLIST_NAME};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutById() {
        return R.layout.rank_list_layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        fragments.add(new DayRanklistFragment());
        fragments.add(new WeekRanklistFragment());
        fragments.add(new TotalRanklistFragment());
        tabViewpage.setAdapter(new MFragmentPagerAdapter(getSupportFragmentManager(), titleArr, fragments));
        tablayout.setupWithViewPager(tabViewpage);
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
