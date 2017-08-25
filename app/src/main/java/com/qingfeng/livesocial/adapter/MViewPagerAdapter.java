package com.qingfeng.livesocial.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;
    private String[] mTitleArr;

    public MViewPagerAdapter(List<View> mViewList, String[] titleArr) {
        this.mViewList = mViewList;
        this.mTitleArr = titleArr;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方推荐写法
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));//添加页卡
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));//删除页卡
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArr[position];//页卡标题
    }
}

