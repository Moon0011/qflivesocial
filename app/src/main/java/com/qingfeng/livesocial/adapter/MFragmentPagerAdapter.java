package com.qingfeng.livesocial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] strArr;
    private List<Fragment> fragments;

    public MFragmentPagerAdapter(FragmentManager fm, String[] strArr, List<Fragment> fragments) {
        super(fm);
        this.strArr = strArr;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return strArr.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strArr[position];
    }
}
