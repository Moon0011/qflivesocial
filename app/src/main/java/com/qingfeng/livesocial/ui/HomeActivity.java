package com.qingfeng.livesocial.ui;

import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.common.AppManager;
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
    private final Class fragmentArray[] = {HomeFragment.class, TestRankFragment.class, TestRankFragment.class, PersonalCenterFragment.class};
    private int mTitleArray[] = {R.string.home, R.string.discover, R.string.message, R.string.personal};
    private int mImageViewArray[] = {R.drawable.tab_home, R.drawable.tab_discover, R.drawable.tab_message, R.drawable.tab_personal};
    private String mTextviewArray[] = {"home", "discover", "message", "personnal"};
    private long mBackPressedTime;
    private static final String TAG_EXIT = "exit";

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

    private long exitTime = 0;

    /**
     * 双击手机的后退键，退出程序！
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//
//    @Override
//    public void onBackPressed() {
//        long curTime = SystemClock.uptimeMillis();
//        if ((curTime - mBackPressedTime) < (3 * 1000)) {
//            AppManager.getAppManager().AppExit();
//        } else {
//            mBackPressedTime = curTime;
//            Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG).show();
//        }
//    }
}
