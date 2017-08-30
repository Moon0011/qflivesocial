package com.qingfeng.livesocial.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MFragmentPagerAdapter;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.ALL;
import static com.qingfeng.livesocial.common.Constants.ATTENTION;

/**
 * Created by hover on 2017/8/29.
 */

public class DiscoverFragment extends BaseFragment {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpage)
    ViewPager tabViewpage;
    private String titleArr[] = {ALL, ATTENTION};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.discover_layout;
    }

    @Override
    protected void initWidget(View root) {
        fragments.add(new DiscoverAllFragment());
        fragments.add(new DiscoverAllFragment());
        tabViewpage.setAdapter(new MFragmentPagerAdapter(getChildFragmentManager(), titleArr, fragments));
        tablayout.setupWithViewPager(tabViewpage);
    }

    @Override
    protected void initData() {
    }
}
