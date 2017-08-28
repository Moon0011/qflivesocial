package com.qingfeng.livesocial.ui.fragment;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MViewPagerAdapter;
import com.qingfeng.livesocial.adapter.RankRecyclerViewAdapter;
import com.qingfeng.livesocial.bean.RankListRespBean;
import com.qingfeng.livesocial.bean.RankListRespBean.RanklistBean;
import com.qingfeng.livesocial.cahce.CacheManager;
import com.qingfeng.livesocial.common.AppOperator;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.ATTENTION_RANKLIST;
import static com.qingfeng.livesocial.common.Constants.ATTENTION_RANKLIST_NAME;
import static com.qingfeng.livesocial.common.Constants.GLAMOUR_RANKLIST;
import static com.qingfeng.livesocial.common.Constants.GLAMOUR_RANKLIST_NAME;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE_ATTENTION_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE_GLAMOUR_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE_RICHER_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE_TOTAL_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;
import static com.qingfeng.livesocial.common.Constants.RICHER_RANKLIST;
import static com.qingfeng.livesocial.common.Constants.RICHER_RANKLIST_NAME;


/**
 * 日榜
 * Created by Administrator on 2017/8/25.
 */
public class TotalRanklistFragment extends BaseFragment {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.tab_viewpage)
    ViewPager tabViewpage;
    private boolean isFirst = true;
    private final String[] mTitleArr = {ATTENTION_RANKLIST_NAME, RICHER_RANKLIST_NAME, GLAMOUR_RANKLIST_NAME};
    private final List<View> mViewList = new ArrayList<>();
    private View view1, view2, view3;
    private LayoutInflater mInflater;
    private RecyclerView recyclelistview1, recyclelistview2, recyclelistview3;
    private SwipeRefreshLayout mSwipeRefreshLayout, mSwipeRefreshLayout2, mSwipeRefreshLayout3;
    private Handler handler = new Handler();
    private RankRecyclerViewAdapter attentionAdapter, richerAdapter, glamourAdapter;
    private List<RanklistBean> datasAttention, datasRicher, datasGlamour;

    @Override
    protected int getLayoutId() {
        return R.layout.ranklist_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.rank_list_childrank_layout, null);
        view2 = mInflater.inflate(R.layout.rank_list_childrank_layout, null);
        view3 = mInflater.inflate(R.layout.rank_list_childrank_layout, null);

        recyclelistview1 = (RecyclerView) view1.findViewById(R.id.recyclelistview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view1.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isFirst) {
                    getAttentionRanklist();
                }
            }
        });

        recyclelistview2 = (RecyclerView) view2.findViewById(R.id.recyclelistview);
        mSwipeRefreshLayout2 = (SwipeRefreshLayout) view2.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout2.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isFirst) {
                    getRicherRanklist();
                }
            }
        });

        recyclelistview3 = (RecyclerView) view3.findViewById(R.id.recyclelistview);
        mSwipeRefreshLayout3 = (SwipeRefreshLayout) view3.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout3.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isFirst) {
                    getGlamourRanklist();
                }
            }
        });

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                final RankListRespBean respBean = (RankListRespBean) CacheManager.readObject(getActivity(), ATTENTION_RANKLIST);
                if (respBean != null) {
                    datasAttention = respBean.getResult();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            attentionAdapter = new RankRecyclerViewAdapter(getActivity(), datasAttention, imageOptions);
                            recyclelistview1.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclelistview1.setHasFixedSize(true);
                            recyclelistview1.setAdapter(attentionAdapter);
                        }
                    });
                }
            }
        });
        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                final RankListRespBean respBean = (RankListRespBean) CacheManager.readObject(getActivity(), RICHER_RANKLIST);
                if (respBean != null) {
                    datasRicher = respBean.getResult();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            richerAdapter = new RankRecyclerViewAdapter(getActivity(), datasRicher, imageOptions);
                            recyclelistview2.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclelistview2.setHasFixedSize(true);
                            recyclelistview2.setAdapter(richerAdapter);
                        }
                    });
                }
            }
        });
        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                final RankListRespBean respBean = (RankListRespBean) CacheManager.readObject(getActivity(), GLAMOUR_RANKLIST);
                if (respBean != null) {
                    datasGlamour = respBean.getResult();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            glamourAdapter = new RankRecyclerViewAdapter(getActivity(), datasGlamour, imageOptions);
                            recyclelistview3.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclelistview3.setHasFixedSize(true);
                            recyclelistview3.setAdapter(glamourAdapter);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void initData() {
        mViewList.clear();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[0]), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleArr[2]));

        MViewPagerAdapter mAdapter = new MViewPagerAdapter(mViewList, mTitleArr);
        tabViewpage.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(tabViewpage);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        if (isFirst) {
            getAttentionRanklist();
            getRicherRanklist();
            getGlamourRanklist();
        }
    }

    /**
     * 关注榜
     */
    private void getAttentionRanklist() {
        RequestParams params = new RequestParams(Urls.RANKLIST);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_TOTAL_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_ATTENTION_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getCareRanklist == " + result);
                final RankListRespBean respBean = new Gson().fromJson(result, RankListRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    datasAttention = respBean.getResult();
                    AppOperator.runOnThread(new Runnable() {
                        @Override
                        public void run() {
                            CacheManager.saveObject(getActivity(), respBean, ATTENTION_RANKLIST);
                        }
                    });
                    attentionAdapter = new RankRecyclerViewAdapter(getActivity(), datasAttention, imageOptions);
                    recyclelistview1.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclelistview1.setHasFixedSize(true);
                    recyclelistview1.setAdapter(attentionAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                    isFirst = false;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
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
     * 土豪榜
     */
    private void getRicherRanklist() {
        RequestParams params = new RequestParams(Urls.RANKLIST);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_TOTAL_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_RICHER_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getRicherRanklist == " + result);
                final RankListRespBean respBean = new Gson().fromJson(result, RankListRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    datasRicher = respBean.getResult();
                    AppOperator.runOnThread(new Runnable() {
                        @Override
                        public void run() {
                            CacheManager.saveObject(getActivity(), respBean, RICHER_RANKLIST);
                        }
                    });
                    richerAdapter = new RankRecyclerViewAdapter(getActivity(), datasRicher, imageOptions);
                    recyclelistview2.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclelistview2.setHasFixedSize(true);
                    recyclelistview2.setAdapter(richerAdapter);
                    mSwipeRefreshLayout2.setRefreshing(false);
                }

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

    /**
     * 魅力榜
     */
    private void getGlamourRanklist() {
        RequestParams params = new RequestParams(Urls.RANKLIST);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_TOTAL_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_GLAMOUR_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getGlamourRanklist == " + result);
                final RankListRespBean respBean = new Gson().fromJson(result, RankListRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    datasGlamour = respBean.getResult();
                    AppOperator.runOnThread(new Runnable() {
                        @Override
                        public void run() {
                            CacheManager.saveObject(getActivity(), respBean, GLAMOUR_RANKLIST);
                        }
                    });
                    glamourAdapter = new RankRecyclerViewAdapter(getActivity(), datasGlamour, imageOptions);
                    recyclelistview3.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclelistview3.setHasFixedSize(true);
                    recyclelistview3.setAdapter(glamourAdapter);
                    mSwipeRefreshLayout3.setRefreshing(false);
                }
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
