package com.qingfeng.livesocial.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.RankRecyclerViewAdapter;
import com.qingfeng.livesocial.bean.AttentionRankListRespBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_CHILDREN_RANKLIST_TYPE_CARE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by hover on 2017/8/27.
 */

public class TestRankFragment extends BaseFragment {

    @Bind(R.id.recyclelistview)
    RecyclerView recyclelistview;

    @Override
    protected int getLayoutId() {
        return R.layout.test_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {
        getCareRanklist();
    }

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
                    List<AttentionRankListRespBean.AttentionRanklistBean> datas = respBean.getResult();
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

}
