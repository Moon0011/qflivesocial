package com.qingfeng.livesocial.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.AttentionAdapter;
import com.qingfeng.livesocial.bean.AttentionRespBean;
import com.qingfeng.livesocial.bean.AttentionRespBean.AttentionBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.PARAM_ATTENTION_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;

/**
 * Created by Administrator on 2017/8/29.
 */

public class AttentionFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.attention_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAtentionList();
            }
        });
    }

    @Override
    protected void initData() {
        getAtentionList();
    }

    private void getAtentionList() {
        RequestParams params = new RequestParams(Urls.ATTPAGE);
        params.addParameter(PARAM_UID, "1");
        params.addParameter(PARAM_TYPE, PARAM_ATTENTION_TYPE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getAtentionList == " + result);
                Gson gson = new Gson();
                AttentionRespBean respBean = gson.fromJson(result, AttentionRespBean.class);
                if (respBean.getResult() != null && respBean.getResult().size() > 0) {
                    List<AttentionBean> datas = respBean.getResult();
                    AttentionAdapter adapter = new AttentionAdapter(getActivity(), datas, imageOptions);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerview.setHasFixedSize(true);
                    recyclerview.setAdapter(adapter);
                } else {
                    showToast("暂时无数据");
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);
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
