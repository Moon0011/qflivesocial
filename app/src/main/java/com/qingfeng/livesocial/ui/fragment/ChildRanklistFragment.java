package com.qingfeng.livesocial.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.RankRecyclerViewAdapter;
import com.qingfeng.livesocial.bean.RankListRespBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE_ATTENTION_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by hover on 2017/8/27.
 */

public class ChildRanklistFragment extends BaseFragment {
    @Bind(R.id.recyclelistview)
    RecyclerView recyclelistview;

    @Override
    protected int getLayoutId() {
        return R.layout.rank_list_childrank_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {

    }

    private void getCareRanklist() {
        RequestParams params = new RequestParams(Urls.RANKLIST);
        params.addParameter(PARAM_PARENT_RANKLIST_TYPE, PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE);
        params.addParameter(PARAM_CHILDREN_RANKLIST_TYPE, PARAM_CHILDREN_RANKLIST_TYPE_ATTENTION_VALUE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getCareRanklist == " + result);
                RankListRespBean respBean = new Gson().fromJson(result, RankListRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    List<RankListRespBean.RanklistBean> datas = respBean.getResult();
                    RankRecyclerViewAdapter adapter = new RankRecyclerViewAdapter(getActivity(), datas, imageOptions);
                    recyclelistview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclelistview.setHasFixedSize(true);
                    recyclelistview.setAdapter(adapter);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
