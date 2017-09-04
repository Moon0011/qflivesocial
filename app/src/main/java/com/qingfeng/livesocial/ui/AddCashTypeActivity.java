package com.qingfeng.livesocial.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/4.
 */

public class AddCashTypeActivity extends BaseActivity {
    @Bind(R.id.tab_viewpage)
    ViewPager mViewpager;
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.ll_wechat)
    LinearLayout llWechat;
    @Bind(R.id.ll_alipay)
    LinearLayout llAlipay;
    @Bind(R.id.img_round_wechat)
    ImageView imgRoundWechat;
    @Bind(R.id.img_round_alipay)
    ImageView imgRoundAlipay;

    private LayoutInflater mInflater;
    private View view1, view2;
    private PagerAdapter mAdpater;
    private List<View> mTabs = new ArrayList<View>();

    @Override
    protected int getLayoutById() {
        return R.layout.add_cash_type_layout;
    }

    @Override
    protected void initView() {
        mInflater = LayoutInflater.from(mContext);
        view1 = mInflater.inflate(R.layout.add_cash_wechat_layout, null);
        view2 = mInflater.inflate(R.layout.add_cash_alipay_layout, null);
        Button btnSumbit1 = (Button) view1.findViewById(R.id.btn_sumbit);
        Button btnSumbit2 = (Button) view2.findViewById(R.id.btn_sumbit);
        btnSumbit1.findViewById(R.id.btn_sumbit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AddCashTypeActivity.this, CashActivity.class);
            }
        });
        btnSumbit2.findViewById(R.id.btn_sumbit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AddCashTypeActivity.this, CashActivity.class);
            }
        });
        mTabs.add(view1);
        mTabs.add(view2);

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewpager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        imgRoundWechat.setImageResource(R.mipmap.add_cash_round_selected);
                        imgRoundAlipay.setImageResource(R.mipmap.add_cash_round);
                        break;
                    case 1:
                        imgRoundAlipay.setImageResource(R.mipmap.add_cash_round_selected);
                        imgRoundWechat.setImageResource(R.mipmap.add_cash_round);
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
        mAdpater = new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mTabs.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTabs.get(position));
            }
        };
        mViewpager.setAdapter(mAdpater);
    }

    @OnClick({R.id.img_arrow_left, R.id.ll_wechat, R.id.ll_alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                break;
            case R.id.ll_wechat:
                mViewpager.setCurrentItem(0);
                imgRoundWechat.setImageResource(R.mipmap.add_cash_round_selected);
                imgRoundAlipay.setImageResource(R.mipmap.add_cash_round);
                break;
            case R.id.ll_alipay:
                mViewpager.setCurrentItem(1);
                imgRoundAlipay.setImageResource(R.mipmap.add_cash_round_selected);
                imgRoundWechat.setImageResource(R.mipmap.add_cash_round);
                break;
        }
    }
}
