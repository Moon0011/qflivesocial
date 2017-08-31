package com.qingfeng.livesocial.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.SendGiftAdapter;
import com.qingfeng.livesocial.bean.AnchorDetailRespBean;
import com.qingfeng.livesocial.bean.SendGiftListRespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.StringUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/28.
 */

public class AnchorDetailActivity extends BaseActivity {

    @Bind(R.id.video_bg_img)
    ImageView videoImg;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.rl_top_head)
    RelativeLayout rlTopHead;
    @Bind(R.id.img_video_player)
    ImageView imgVideoPlayer;
    @Bind(R.id.btn_label1)
    Button btnLabel1;
    @Bind(R.id.btn_label2)
    Button btnLabel2;
    @Bind(R.id.btn_label3)
    Button btnLabel3;
    @Bind(R.id.btn_label4)
    Button btnLabel4;
    @Bind(R.id.ll_labels1)
    LinearLayout llLabels1;
    @Bind(R.id.tv_signature)
    TextView tvSignature;
    @Bind(R.id.ll_gift_container)
    LinearLayout llGiftContainer;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;
    @Bind(R.id.tv_voice_time)
    TextView tvVoiceTime;
    @Bind(R.id.tv_comment_rating)
    TextView tvCommentRating;
    @Bind(R.id.tv_comment_num)
    TextView tvCommentNum;
    @Bind(R.id.tv_total_talktime)
    TextView tvTotalTalktime;
    @Bind(R.id.tv_fans)
    TextView tv_fans;
    private String uid;

    @Override
    protected int getLayoutById() {
        return R.layout.anchor_detail_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        uid = getIntent().getExtras().getString(PARAM_UID);
        getAnchorDetail(uid);
    }

    private void getAnchorDetail(String uid) {
        RequestParams params = new RequestParams(Urls.WEBSITE);
        params.addParameter(PARAM_UID, uid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getAnchorDetail == " + result);
                AnchorDetailRespBean respBean = new Gson().fromJson(result, AnchorDetailRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    AnchorDetailRespBean.AnchorDetailBean bean = respBean.getResult();
                    tvNickname.setText(bean.getNickname());
                    x.image().bind(videoImg,
                            bean.getVideo(),
                            imageOptions,
                            null);
                    tvIntroduce.setText(bean.getSex() + "  " + bean.getAge() + "  " + bean.getConstellation() + "  " + bean.getAddress());
                    tvVoiceTime.setText(bean.getVoice());
                    tvCommentRating.setText("好评度" + bean.getRating());
                    tvCommentNum.setText(bean.getCommentnum() + "条评论");
                    tvTotalTalktime.setText(bean.getTotaltime() + "分钟");
                    tvSignature.setText(bean.getSignature());
                    tv_fans.setText(String.valueOf(bean.getAttentionnum()));
                    List<Button> btnContainer = new ArrayList<Button>();
                    btnContainer.add(btnLabel1);
                    btnContainer.add(btnLabel2);
                    btnContainer.add(btnLabel3);
                    btnContainer.add(btnLabel4);
                    if (!StringUtils.isEmpty(bean.getLabels())) {
                        String[] labelArr = bean.getLabels().split(",");
                        for (int k = 0; k < labelArr.length; k++) {
                            btnContainer.get(k).setVisibility(View.VISIBLE);
                            btnContainer.get(k).setText(labelArr[k]);
                        }
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

    @OnClick({R.id.img_arrow_left, R.id.ll_call_evaluation, R.id.ll_tab_sendgift, R.id.ll_tab_msgself, R.id.ll_tab_phone, R.id.ll_tab_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.ll_call_evaluation:
                Bundle b = new Bundle();
                b.putString(PARAM_UID, uid);
                gotoActivityWithBundle(AnchorDetailActivity.this, CallEvaluationAcitivity.class, b);
                break;
            case R.id.ll_tab_sendgift:
                SendGiftFragment dialog = new SendGiftFragment();
                dialog.show(getFragmentManager(), "sendgift");
                break;
            case R.id.ll_tab_msgself:
                break;
            case R.id.ll_tab_phone:
                break;
            case R.id.ll_tab_video:
                break;
        }
    }

    class SendGiftFragment extends DialogFragment {
        private RecyclerView recyclerView;
        private Button btnSendGift;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.send_gift_fragment_layout, container);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            btnSendGift = (Button) view.findViewById(R.id.btn_sendgift);
            btnSendGift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return view;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            sendGiftList();
        }

        private void sendGiftList() {
            RequestParams params = new RequestParams(Urls.SEND_GIFT_LIST);
            params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    LogUtil.e("sendGiftList == " + result);
                    Gson gson = new Gson();
                    final SendGiftListRespBean bean = gson.fromJson(result, SendGiftListRespBean.class);
                    SendGiftAdapter adapter = new SendGiftAdapter(getActivity(), bean.getResult().getGiftinfo(), imageOptions);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
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
}
