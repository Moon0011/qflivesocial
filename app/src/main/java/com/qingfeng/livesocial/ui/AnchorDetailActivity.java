package com.qingfeng.livesocial.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.ui.fragment.AnchorDetailFragment;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;

/**
 * Created by Administrator on 2017/8/28.
 */

public class AnchorDetailActivity extends BaseActivity {

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    private final Class fragmentArray[] = {AnchorDetailFragment.class, AnchorDetailFragment.class, AnchorDetailFragment.class, AnchorDetailFragment.class};
    private int mTitleArray[] = {R.string.send_gift, R.string.msg_self, R.string.audio_chat, R.string.video_chat};
    private int mImageViewArray[] = {R.drawable.tab_anchor_gift, R.drawable.tab_anchor_chat, R.drawable.tab_anchor_phone, R.drawable.tab_anchor_video};
    private String mTextviewArray[] = {"home", "discover", "message", "personnal"};
    private LayoutInflater layoutInflater;

    @Override
    protected int getLayoutById() {
        return R.layout.anchor_detail_layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        String uid = (String) getIntent().getExtras().get(PARAM_UID);
        layoutInflater = LayoutInflater.from(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);
        int fragmentCount = fragmentArray.length;
        for (int i = 0; i < fragmentCount; ++i) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            Bundle bundle = new Bundle();
            bundle.putString(PARAM_UID, uid);
            mTabHost.addTab(tabSpec, fragmentArray[i], bundle);
            mTabHost.getTabWidget().setDividerDrawable(null);
        }
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
