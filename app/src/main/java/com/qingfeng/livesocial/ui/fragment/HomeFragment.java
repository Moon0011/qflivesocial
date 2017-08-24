package com.qingfeng.livesocial.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.PersonRecommendAdapter;
import com.qingfeng.livesocial.bean.SlideRepsBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.widget.HorizontalListView;
import com.qingfeng.livesocial.widget.MViewPager;
import com.qingfeng.livesocial.widget.SlideShowView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/8/22.
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.tab_viewpage)
    MViewPager mViewPager;
    @Bind(R.id.slideshowview)
    SlideShowView mSlideShowView;

    HorizontalListView horizontalListView;
    private View view1, view2, view3, view4;
    private LayoutInflater mInflater;
    private final List<View> mViewList = new ArrayList<>();
    private final String[] mTitleArr = {"全部", "认证", "人气", "新秀"};

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.home_tab_all_layout, null);
        view2 = mInflater.inflate(R.layout.home_tab_authen_layout, null);
        view3 = mInflater.inflate(R.layout.home_tab_popularity_layout, null);
        view4 = mInflater.inflate(R.layout.home_tab_youngshow_layout, null);
        horizontalListView = (HorizontalListView) view1.findViewById(R.id.hor_listview);
    }

    @Override
    protected void initData() {
        mViewList.clear();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);

        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[0]), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[3]));

        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        PersonRecommendAdapter personRecommendAdapter = new PersonRecommendAdapter(getActivity());
        horizontalListView.setAdapter(personRecommendAdapter);
        getSlideImg();
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleArr[position];//页卡标题
        }
    }

    private void getSlideImg() {
        RequestParams params = new RequestParams(Urls.SLIDE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("perfectInfoSucc == " + result);
                SlideRepsBean slideRepsBean = new Gson().fromJson(result, SlideRepsBean.class);
                List<SlideRepsBean.SlideBean> slideBeanList = slideRepsBean.getResult();
                String[] imageUrls = new String[slideBeanList.size()];
                for (int i = 0; i < slideBeanList.size(); i++) {
                    imageUrls[i] = slideBeanList.get(i).getPic();
                }
                mSlideShowView.bindImgSource(imageUrls);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
