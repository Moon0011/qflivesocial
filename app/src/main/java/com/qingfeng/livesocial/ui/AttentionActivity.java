package com.qingfeng.livesocial.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MFragmentPagerAdapter;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.AttentionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.ATTENTION;
import static com.qingfeng.livesocial.common.Constants.FANS;

/**
 * Created by Administrator on 2017/8/29.
 */

public class AttentionActivity extends BaseActivity {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpage)
    ViewPager tabViewpage;
    private String titleArr[] = {ATTENTION, FANS};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutById() {
        return R.layout.attention_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        fragments.add(new AttentionFragment());
        fragments.add(new AttentionFragment());
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
