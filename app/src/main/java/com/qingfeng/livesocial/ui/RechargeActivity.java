package com.qingfeng.livesocial.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.RechargePagerAdapter;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.AlipayFragment;
import com.qingfeng.livesocial.ui.fragment.UnionPayFragment;
import com.qingfeng.livesocial.ui.fragment.WechatPayFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/4.
 */

public class RechargeActivity extends BaseActivity {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.tab_viewpage)
    ViewPager mViewPager;
    @Bind(R.id.tv_paytype)
    TextView tvPayType;
    private String[] tabTitles = new String[]{"支付宝", "微信", "银联"};
    private int[] tabIcons = new int[]{R.mipmap.log_alipay, R.mipmap.log_wechat, R.mipmap.log_unionpay};
    private List titles;
    private List fragments;

    @Override
    protected int getLayoutById() {
        return R.layout.recharge_layout;
    }

    @Override
    protected void initView() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        tvPayType.setText("支付方式: 支付宝");
                        break;
                    case 1:
                        tvPayType.setText("支付方式: 微信");
                        break;
                    case 2:
                        tvPayType.setText("支付方式: 银联");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new AlipayFragment());
        fragments.add(new WechatPayFragment());
        fragments.add(new UnionPayFragment());
        titles = new ArrayList<>();
        titles.add("alipay");
        titles.add("wechat");
        titles.add("unionpay");
        RechargePagerAdapter adapter = new RechargePagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    protected View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recharge_tab_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.img_icon);
        img.setImageResource(tabIcons[position]);
        return view;
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));
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
