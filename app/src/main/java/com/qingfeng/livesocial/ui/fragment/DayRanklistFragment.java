package com.qingfeng.livesocial.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.MViewPagerAdapter;
import com.qingfeng.livesocial.adapter.RanklistAdapter;
import com.qingfeng.livesocial.bean.AttentionRankListRespBean;
import com.qingfeng.livesocial.bean.AttentionRankListRespBean.AttentionRanklistBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.CARE_RANKLIST_NAME;
import static com.qingfeng.livesocial.common.Constants.GLAMOUR_RANKLIST_NAME;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE_CARE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;
import static com.qingfeng.livesocial.common.Constants.RICHER_RANKLIST_NAME;


/**
 * 日榜
 * Created by Administrator on 2017/8/25.
 */
public class DayRanklistFragment extends BaseFragment {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.tab_viewpage)
    ViewPager tabViewpage;
    private final String[] mTitleArr = {CARE_RANKLIST_NAME, RICHER_RANKLIST_NAME, GLAMOUR_RANKLIST_NAME};
    private final List<View> mViewList = new ArrayList<>();
    private View view1, view2, view3;
    private LayoutInflater mInflater;
    private ListView rankListView;

    @Override
    protected int getLayoutId() {
        return R.layout.day_ranklist_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.rank_list_childrank_layout, null);
        view2 = mInflater.inflate(R.layout.rank_list_childrank_layout, null);
        view3 = mInflater.inflate(R.layout.rank_list_childrank_layout, null);

        rankListView = (ListView) view1.findViewById(R.id.rank_listview);
    }

    @Override
    protected void initData() {
        getCareRanklist();

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
    }

    /**
     * 关注榜
     */
    private void getCareRanklist() {
        RequestParams params = new RequestParams(Urls.RANKLIST);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_CARE_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getCareRanklist == " + result);
                AttentionRankListRespBean respBean = new Gson().fromJson(result, AttentionRankListRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    List<AttentionRanklistBean> datas = respBean.getResult();
                    RanklistAdapter adapter = new RanklistAdapter(getActivity(), datas, imageOptions);
                    rankListView.setAdapter(adapter);
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
     * 土豪榜
     */
    private void getRicherRanklist() {
        RequestParams params = new RequestParams(Urls.SLIDE);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_CARE_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getSlideImg == " + result);

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
        RequestParams params = new RequestParams(Urls.SLIDE);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_CARE_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getSlideImg == " + result);

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
