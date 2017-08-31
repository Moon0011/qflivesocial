package com.qingfeng.livesocial.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.HRecyclerViewAdapter;
import com.qingfeng.livesocial.adapter.MViewPagerAdapter;
import com.qingfeng.livesocial.bean.LiveAnchorRespBean;
import com.qingfeng.livesocial.bean.RecommedRespBean;
import com.qingfeng.livesocial.bean.SlideRepsBean;
import com.qingfeng.livesocial.cahce.CacheManager;
import com.qingfeng.livesocial.common.AppOperator;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.AnchorDetailActivity;
import com.qingfeng.livesocial.ui.RanklistActivity;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.util.StringUtils;
import com.qingfeng.livesocial.widget.MSwipeRefreshLayout;
import com.qingfeng.livesocial.widget.MViewPager;
import com.qingfeng.livesocial.widget.SlideShowView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.HOME_POPULAR_ANCHOR;
import static com.qingfeng.livesocial.common.Constants.HOME_RECOMMEND_ANCHOR;
import static com.qingfeng.livesocial.common.Constants.HOME_SLIDE_IMG;
import static com.qingfeng.livesocial.common.Constants.HOME_TOTAL_ANCHOR;
import static com.qingfeng.livesocial.common.Constants.HOME_YOUNGSHOW_ANCHOR;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;


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
    @Bind(R.id.swiperefreshlayout)
    MSwipeRefreshLayout mSwipeRefreshLayout;

    RecyclerView hRecyclerView;
    LinearLayout ll_all_container, ll_popular_container, ll_youngshow_container;
    private View view1, view2, view3, view4;
    private LayoutInflater mInflater;
    private final List<View> mViewList = new ArrayList<>();
    private final String[] mTitleArr = {"全部", "认证", "人气", "新秀"};
    private static final String TYPE_ALL_USER = "1"; //全部
    private static final String TYPE_POPULAR_USER = "2";//人气
    private static final String TYPE_YOUNGSHOW_USER = "3";//新秀
    private boolean isFirst = true;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isFirst) {
                    getRecommendData();
                    getAllUserInfo();
                    getPopularUserInfo();
                    getYoungShowUserInfo();
                }
            }
        });

        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.home_tab_all_layout, null);
        view2 = mInflater.inflate(R.layout.home_tab_authen_layout, null);
        view3 = mInflater.inflate(R.layout.home_tab_popularity_layout, null);
        view4 = mInflater.inflate(R.layout.home_tab_youngshow_layout, null);
        hRecyclerView = (RecyclerView) view1.findViewById(R.id.hor_listview);
        ll_all_container = (LinearLayout) view1.findViewById(R.id.ll_container);
        ll_popular_container = (LinearLayout) view3.findViewById(R.id.ll_container);
        ll_youngshow_container = (LinearLayout) view4.findViewById(R.id.ll_container);
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

        MViewPagerAdapter mAdapter = new MViewPagerAdapter(mViewList, mTitleArr);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        SlideRepsBean respon = (SlideRepsBean) CacheManager.readObject(getActivity(), HOME_SLIDE_IMG);
                        if (respon == null || isFirst) {
                            getSlideImg();
                        } else {
                            bindSlideData(respon);
                        }
                    }
                });
            }
        });
        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        RecommedRespBean respon = (RecommedRespBean) CacheManager.readObject(getActivity(), HOME_RECOMMEND_ANCHOR);
                        if (respon == null || isFirst) {
                            getRecommendData();
                        } else {
                            bindRecommendData(respon);
                        }
                    }
                });
            }
        });

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LiveAnchorRespBean respon = (LiveAnchorRespBean) CacheManager.readObject(getActivity(), HOME_TOTAL_ANCHOR);
                        if (respon == null || isFirst) {
                            getAllUserInfo();
                        } else {
                            bindData(respon, ll_all_container);
                        }
                    }
                });
            }
        });

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LiveAnchorRespBean respon = (LiveAnchorRespBean) CacheManager.readObject(getActivity(), HOME_POPULAR_ANCHOR);
                        if (respon == null || isFirst) {
                            getPopularUserInfo();
                        } else {
                            bindData(respon, ll_popular_container);
                        }
                    }
                });
            }
        });

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LiveAnchorRespBean respon = (LiveAnchorRespBean) CacheManager.readObject(getActivity(), HOME_YOUNGSHOW_ANCHOR);
                        if (respon == null || isFirst) {
                            getYoungShowUserInfo();
                        } else {
                            bindData(respon, ll_youngshow_container);
                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.ranklist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ranklist:
                gotoActivity(getActivity(), RanklistActivity.class);
                break;
        }
    }

    /**
     * 轮播图
     */
    private void getSlideImg() {
        RequestParams params = new RequestParams(Urls.SLIDE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getSlideImg == " + result);
                final SlideRepsBean respon = new Gson().fromJson(result, SlideRepsBean.class);
                bindSlideData(respon);
                AppOperator.runOnThread(new Runnable() {
                    @Override
                    public void run() {
                        if (respon != null && respon.getResult() != null) {
                            CacheManager.saveObject(getActivity(), respon, HOME_SLIDE_IMG);
                        }
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 全部
     */
    private void getAllUserInfo() {
        RequestParams params = new RequestParams(Urls.RECOMMEND);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, TYPE_ALL_USER);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getAllUserInfo == " + result);
                Gson gson = new Gson();
                final LiveAnchorRespBean bean = gson.fromJson(result, LiveAnchorRespBean.class);
                bindData(bean, ll_all_container);
                AppOperator.runOnThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bean != null && bean.getResult() != null) {
                            CacheManager.saveObject(getActivity(), bean, HOME_TOTAL_ANCHOR);
                        }
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }


    /**
     * 人气
     */
    private void getPopularUserInfo() {
        RequestParams params = new RequestParams(Urls.RECOMMEND);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, TYPE_POPULAR_USER);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getPopularUserInfo == " + result);
                Gson gson = new Gson();
                final LiveAnchorRespBean bean = gson.fromJson(result, LiveAnchorRespBean.class);
                bindData(bean, ll_popular_container);
                AppOperator.runOnThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bean != null && bean.getResult() != null) {
                            CacheManager.saveObject(getActivity(), bean, HOME_POPULAR_ANCHOR);
                        }
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 新秀
     */
    private void getYoungShowUserInfo() {
        RequestParams params = new RequestParams(Urls.RECOMMEND);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, TYPE_YOUNGSHOW_USER);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getPopularUserInfo == " + result);
                Gson gson = new Gson();
                final LiveAnchorRespBean bean = gson.fromJson(result, LiveAnchorRespBean.class);
                bindData(bean, ll_youngshow_container);
                AppOperator.runOnThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bean != null && bean.getResult() != null) {
                            CacheManager.saveObject(getActivity(), bean, HOME_YOUNGSHOW_ANCHOR);
                        }
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 今日推荐数据
     */
    private void getRecommendData() {
        RequestParams params = new RequestParams(Urls.DAY_RECOMMEND);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getRecommendData == " + result);
                final RecommedRespBean respon = new Gson().fromJson(result, RecommedRespBean.class);
                bindRecommendData(respon);
                AppOperator.runOnThread(new Runnable() {
                    @Override
                    public void run() {
                        if (respon != null && respon.getResult() != null) {
                            CacheManager.saveObject(getActivity(), respon, HOME_RECOMMEND_ANCHOR);
                        }
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
                isFirst = false;
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void bindSlideData(SlideRepsBean respon) {
        if (PARAM_Y.equals(respon.getMsg()) && respon.getResult() != null) {
            List<SlideRepsBean.SlideBean> slideBeanList = respon.getResult();
            String[] imageUrls = new String[slideBeanList.size()];
            for (int i = 0; i < slideBeanList.size(); i++) {
                imageUrls[i] = slideBeanList.get(i).getPic();
            }
            mSlideShowView.bindImgSource(imageUrls);
        }
    }

    private void bindRecommendData(RecommedRespBean respon) {
        if (PARAM_Y.equals(respon.getMsg()) && respon.getResult() != null) {
            HRecyclerViewAdapter hRecyclerViewAdapter = new HRecyclerViewAdapter(getActivity(), respon.getResult(), imageOptions);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            hRecyclerView.setLayoutManager(layoutManager);
            hRecyclerView.setHasFixedSize(true);
            hRecyclerView.setAdapter(hRecyclerViewAdapter);
        }
    }

    private void bindData(final LiveAnchorRespBean bean, LinearLayout llContainer) {
        if (PARAM_Y.equals(bean.getMsg()) && bean.getResult() != null) {
            final List<LiveAnchorRespBean.LiveAnchorBean> datas = bean.getResult();
            int size = datas.size() % 2 == 0 ? datas.size() / 2 : datas.size() / 2 + 1;
            if (size <= 0) {
                return;
            }
            int m = 0;
            for (int i = 0; i < size; i++) {
                LinearLayout ll = (LinearLayout) mInflater.inflate(R.layout.anchor_show_layout, null);
                FrameLayout anchor1 = (FrameLayout) ll.findViewById(R.id.fl_anchor1);
                FrameLayout anchor2 = (FrameLayout) ll.findViewById(R.id.fl_anchor2);
                LinearLayout llitem1 = (LinearLayout) ll.findViewById(R.id.ll_anchor_item1);
                LinearLayout llitem2 = (LinearLayout) ll.findViewById(R.id.ll_anchor_item2);
                TextView nickName1 = (TextView) ll.findViewById(R.id.tv_nickname1);
                TextView nickName2 = (TextView) ll.findViewById(R.id.tv_nickname2);
                TextView age = (TextView) ll.findViewById(R.id.tv_age);
                TextView age2 = (TextView) ll.findViewById(R.id.tv_age2);
                TextView signature1 = (TextView) ll.findViewById(R.id.tv_signature1);
                TextView signature2 = (TextView) ll.findViewById(R.id.tv_signature2);
                ImageView imageHead1 = (ImageView) ll.findViewById(R.id.img_head1);
                ImageView imageHead2 = (ImageView) ll.findViewById(R.id.img_head2);
                Button btnlabel11 = (Button) ll.findViewById(R.id.btn_label11);
                Button btnlabel12 = (Button) ll.findViewById(R.id.btn_label12);
                Button btnlabel13 = (Button) ll.findViewById(R.id.btn_label13);
                List<Button> btnContainer1 = new ArrayList<Button>();
                btnContainer1.add(btnlabel11);
                btnContainer1.add(btnlabel12);
                btnContainer1.add(btnlabel13);
                Button btnlabel21 = (Button) ll.findViewById(R.id.btn_label21);
                Button btnlabel22 = (Button) ll.findViewById(R.id.btn_label22);
                Button btnlabel23 = (Button) ll.findViewById(R.id.btn_label23);
                List<Button> btnContainer2 = new ArrayList<Button>();
                btnContainer2.add(btnlabel21);
                btnContainer2.add(btnlabel22);
                btnContainer2.add(btnlabel23);
                if (size > 1) {
                    if (i == size - 1) {
                        int p = datas.size() - 1;
                        nickName1.setText(datas.get(p).getNickname());
                        age.setText(datas.get(p).getAge());
                        signature1.setText(datas.get(p).getSignature());
                        String labels1 = datas.get(p).getLabels();
                        if (!StringUtils.isEmpty(labels1)) {
                            String[] labelArr = labels1.split(",");
                            for (int k = 0; k < labelArr.length; k++) {
                                btnContainer1.get(k).setVisibility(View.VISIBLE);
                                btnContainer1.get(k).setText(labelArr[k]);
                            }
                        }
                        x.image().bind(imageHead1,
                                datas.get(p).getAnchorpic(),
                                imageOptions,
                                null);
                        final int pos1 = p;
                        anchor1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle b = new Bundle();
                                String uid = String.valueOf(datas.get(pos1).getUid());
                                b.putString(PARAM_UID, uid);
                                gotoActivityWithBundle(getActivity(), AnchorDetailActivity.class, b);
                            }
                        });
                        llitem2.setVisibility(View.INVISIBLE);
                    } else {
                        nickName1.setText(datas.get(m).getNickname());
                        age.setText(datas.get(m).getAge());
                        signature1.setText(datas.get(m).getSignature());
                        String labels1 = datas.get(m).getLabels();
                        if (!StringUtils.isEmpty(labels1)) {
                            String[] labelArr = labels1.split(",");
                            for (int k = 0; k < labelArr.length; k++) {
                                btnContainer1.get(k).setVisibility(View.VISIBLE);
                                btnContainer1.get(k).setText(labelArr[k]);
                            }
                        }
                        x.image().bind(imageHead1,
                                datas.get(m).getAnchorpic(),
                                imageOptions,
                                null);
                        final int pos1 = m;
                        anchor1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle b = new Bundle();
                                String uid = String.valueOf(datas.get(pos1).getUid());
                                b.putString(PARAM_UID, uid);
                                gotoActivityWithBundle(getActivity(), AnchorDetailActivity.class, b);
                            }
                        });

                        nickName2.setText(datas.get(m + 1).getNickname());
                        age2.setText(datas.get(m + 1).getAge());
                        signature2.setText(datas.get(m + 1).getSignature());
                        String labels2 = datas.get(m + 1).getLabels();
                        if (!StringUtils.isEmpty(labels2)) {
                            String[] labelArr = labels2.split(",");
                            for (int k = 0; k < labelArr.length; k++) {
                                btnContainer2.get(k).setVisibility(View.VISIBLE);
                                btnContainer2.get(k).setText(labelArr[k]);
                            }
                        }
                        x.image().bind(imageHead2,
                                datas.get(m + 1).getAnchorpic(),
                                imageOptions,
                                null);
                        final int pos2 = m + 1;
                        anchor2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle b = new Bundle();
                                b.putString(PARAM_UID, String.valueOf(datas.get(pos2).getUid()));
                                gotoActivityWithBundle(getActivity(), AnchorDetailActivity.class, b);
                            }
                        });

                        m += 2;
                    }
                } else {
                    i = m;
                    nickName1.setText(datas.get(i).getNickname());
                    age.setText(datas.get(i).getAge());
                    signature1.setText(datas.get(i).getSignature());
                    String labels1 = datas.get(i).getLabels();
                    if (!StringUtils.isEmpty(labels1)) {
                        String[] labelArr = labels1.split(",");
                        for (int k = 0; k < labelArr.length; k++) {
                            btnContainer1.get(k).setVisibility(View.VISIBLE);
                            btnContainer1.get(k).setText(labelArr[k]);
                        }
                    }
                    x.image().bind(imageHead1,
                            datas.get(i).getAnchorpic(),
                            imageOptions,
                            null);
                    final int pos1 = i;
                    anchor1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle b = new Bundle();
                            String uid = String.valueOf(datas.get(pos1).getUid());
                            b.putString(PARAM_UID, uid);
                            gotoActivityWithBundle(getActivity(), AnchorDetailActivity.class, b);
                        }
                    });

                    nickName2.setText(datas.get(i + 1).getNickname());
                    age2.setText(datas.get(i + 1).getAge());
                    signature2.setText(datas.get(i + 1).getSignature());
                    String labels2 = datas.get(i + 1).getLabels();
                    if (!StringUtils.isEmpty(labels2)) {
                        String[] labelArr = labels2.split(",");
                        for (int k = 0; k < labelArr.length; k++) {
                            btnContainer2.get(k).setVisibility(View.VISIBLE);
                            btnContainer2.get(k).setText(labelArr[k]);
                        }
                    }
                    x.image().bind(imageHead2,
                            datas.get(i + 1).getAnchorpic(),
                            imageOptions,
                            null);
                    final int pos2 = i + 1;
                    anchor2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle b = new Bundle();
                            b.putString(PARAM_UID, String.valueOf(datas.get(pos2).getUid()));
                            gotoActivityWithBundle(getActivity(), AnchorDetailActivity.class, b);
                        }
                    });

                    m += 2;
                }
                llContainer.addView(ll);
            }
        }
    }
}
