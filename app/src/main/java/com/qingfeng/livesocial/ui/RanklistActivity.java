package com.qingfeng.livesocial.ui;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MFragmentPagerAdapter;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.DayRanklistFragment;
import com.qingfeng.livesocial.widget.MViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/8/25.
 */
public class RanklistActivity extends BaseActivity {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpage)
    MViewPager tabViewpage;
    private String titleArr[] = {"日榜", "周榜", "总榜"};
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
        fragments.add(new DayRanklistFragment());
        fragments.add(new DayRanklistFragment());
        tabViewpage.setAdapter(new MFragmentPagerAdapter(getSupportFragmentManager(), titleArr, fragments));
        tablayout.setupWithViewPager(tabViewpage);
    }
}
