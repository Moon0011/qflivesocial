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

import static com.qingfeng.livesocial.common.Constants.MY_GIFT;
import static com.qingfeng.livesocial.common.Constants.SEND_GIFT;

/**
 * Created by hover on 2017/8/29.
 */

public class DiscoverFragment extends BaseFragment {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpage)
    ViewPager tabViewpage;
    private String titleArr[] = {MY_GIFT, SEND_GIFT};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.discover_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {
        fragments.add(new DiscoverAllFragment());
        fragments.add(new DiscoverAllFragment());
        tabViewpage.setAdapter(new MFragmentPagerAdapter(getFragmentManager(), titleArr, fragments));
        tablayout.setupWithViewPager(tabViewpage);
    }
}
