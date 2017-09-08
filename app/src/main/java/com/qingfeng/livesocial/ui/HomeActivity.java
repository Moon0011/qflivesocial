package com.qingfeng.livesocial.ui;

import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.HomeFragment;
import com.qingfeng.livesocial.ui.fragment.PersonalCenterFragment;
import com.qingfeng.livesocial.ui.fragment.TestRankFragment;

/**
 * Created by Administrator on 2017/8/22.
 */

public class HomeActivity extends BaseActivity {
    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private final Class fragmentArray[] = {HomeFragment.class, TestRankFragment.class, PersonalCenterFragment.class};
    private int mTitleArray[] = {R.string.home, R.string.message, R.string.personal};
    private int mImageViewArray[] = {R.drawable.tab_home, R.drawable.tab_message, R.drawable.tab_personal};
    private String mTextviewArray[] = {"home", "message", "personnal"};
    private long exitTime = 0;
    private long time = 0;

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            exit();
        }
        return true;
    }

    private void exit() {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
            showToast("再点击一次退出应用程序");
        } else {
            removeALLActivity();
        }
    }
}
