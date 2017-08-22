package com.qingfeng.livesocial.ui;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.HomeFragment;

/**
 * Created by Administrator on 2017/8/22.
 */

public class HomeActivity extends BaseActivity {
    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private final Class fragmentArray[] = {HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class};
    private int mTitleArray[] = {R.string.home, R.string.discover, R.string.message, R.string.personal};
    private int mImageViewArray[] = {R.drawable.tab_home, R.drawable.tab_discover, R.drawable.tab_message, R.drawable.tab_personal};
    private String mTextviewArray[] = {"home", "discover", "message", "personnal"};

    @Override
    protected int getLayoutById() {
        return R.layout.home_layout;
    }

    @Override
    protected void initView() {
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);
        int fragmentCount = fragmentArray.length;
        for (int i = 0; i < fragmentCount; ++i) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.getTabWidget().setDividerDrawable(null);
        }
    }

    @Override
    protected void initData() {
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.home_tab_layout, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mImageViewArray[index]);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTitleArray[index]);
        return view;
    }
}
