package com.qingfeng.livesocial.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MViewPagerAdapter;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.widget.MViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/8/25.
 */

public class DayRanklistFragment extends BaseFragment {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.tab_viewpage)
    MViewPager tabViewpage;
    private final String[] mTitleArr = {"关注榜", "土豪榜", "魅力榜"};
    private final List<View> mViewList = new ArrayList<>();
    private View view1, view2, view3;
    private LayoutInflater mInflater;

    public static DayRanklistFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        DayRanklistFragment testFragment = new DayRanklistFragment();
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test;
    }

    @Override
    protected void initWidget(View root) {
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.home_tab_authen_layout, null);
        view2 = mInflater.inflate(R.layout.home_tab_authen_layout, null);
        view3 = mInflater.inflate(R.layout.home_tab_authen_layout, null);
    }

    @Override
    protected void initData() {
        mViewList.clear();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[0]), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[2]));

        MViewPagerAdapter mAdapter = new MViewPagerAdapter(mViewList, mTitleArr);
        tabViewpage.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(tabViewpage);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }
}
