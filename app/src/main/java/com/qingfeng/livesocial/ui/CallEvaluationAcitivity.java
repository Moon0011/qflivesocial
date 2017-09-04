package com.qingfeng.livesocial.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.CommendAdapter;
import com.qingfeng.livesocial.bean.CallEvaluationRespBean;
import com.qingfeng.livesocial.bean.CallEvaluationRespBean.ResultBean.CommentinfoBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CallEvaluationAcitivity extends BaseActivity implements CommendAdapter.OnItemClickListener {
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.img_roompic)
    ImageView imgRoompic;
    @Bind(R.id.tv_anchorpic)
    RoundedImageView tvAnchorpic;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_curr_roomnum)
    TextView tvCurrRoomnum;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private String uid;
    private List<CommentinfoBean> commentList;

    @Override
    protected int getLayoutById() {
        return R.layout.call_evaluation_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        uid = getIntent().getExtras().getString(PARAM_UID);
        getCallEvaluation(uid);
    }

    private void getCallEvaluation(String uid) {
        RequestParams params = new RequestParams(Urls.CALL_EVALUATION);
        params.addParameter(PARAM_UID, "29");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getCallEvaluation == " + result);
                CallEvaluationRespBean respBean = new Gson().fromJson(result, CallEvaluationRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    if (null != respBean.getResult()) {
                        x.image().bind(imgRoompic,
                                respBean.getResult().getUserinfo().getRoompic(),
                                imageOptions,
                                null);
                        x.image().bind(tvAnchorpic,
                                respBean.getResult().getUserinfo().getAnchorpic(),
                                imageOptions,
                                null);
                        tvNickname.setText(respBean.getResult().getUserinfo().getNickname());
                        tvCurrRoomnum.setText(String.valueOf(respBean.getResult().getUserinfo().getCurroomnum()));
                        tvAge.setText(String.valueOf(respBean.getResult().getUserinfo().getAge()));
                        commentList = respBean.getResult().getCommentinfo();
                        CommendAdapter adapter = new CommendAdapter(mContext, commentList, imageOptions);
                        adapter.setOnItemClickListener(CallEvaluationAcitivity.this);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setAdapter(adapter);
                    }
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

    @OnClick({R.id.img_arrow_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
//            case R.id.ll_evaluation_detail:
//                Bundle b = new Bundle();
//                b.putString(PARAM_UID, uid);
//                gotoActivityWithBundle(CallEvaluationAcitivity.this, EvaluationDetailAcitivity.class, b);
//                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Bundle b = new Bundle();
        b.putSerializable("commedbean", commentList.get(position));
        gotoActivityWithBundle(CallEvaluationAcitivity.this, EvaluationDetailAcitivity.class, b);
    }
}
