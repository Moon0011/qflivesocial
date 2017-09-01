package com.qingfeng.livesocial.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.GiftRespBean;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.widget.MSwipeRefreshLayout;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;

import static com.qingfeng.livesocial.common.Constants.PARAM_GIFT_SEND_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 */

public class SendGiftFragment extends BaseFragment {
    @Bind(R.id.ll_container)
    LinearLayout llMaxContainer;
    @Bind(R.id.swiperefreshlayout)
    MSwipeRefreshLayout mSwipeRefreshLayout;
    private LayoutInflater mInflater;

    @Override
    protected int getLayoutId() {
        return R.layout.my_gift_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        mInflater = LayoutInflater.from(getActivity());
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                llMaxContainer.removeAllViews();
                sendGift();
            }
        });
    }

    @Override
    protected void initData() {
        sendGift();
    }

    private void sendGift() {
        RequestParams params = new RequestParams(Urls.GIFT);
        params.addParameter(PARAM_UID, "4");
        params.addParameter(PARAM_TYPE, PARAM_GIFT_SEND_TYPE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("sendGift == " + result);
                GiftRespBean respBean = new Gson().fromJson(result, GiftRespBean.class);
                if (respBean != null && PARAM_Y.equals(respBean.getMsg())) {
                    List<GiftRespBean.GiftBean> datas = respBean.getResult();
                    if (datas != null && datas.size() > 0) {
                        for (int i = 0; i < datas.size(); i++) {
                            LinearLayout llParentContainer = (LinearLayout) mInflater.inflate(R.layout.gift_item_layout, null);
                            TextView tvTime = (TextView) llParentContainer.findViewById(R.id.tv_time);
                            LinearLayout llChildContainer = (LinearLayout) llParentContainer.findViewById(R.id.ll_container_child);
                            tvTime.setText(datas.get(i).getDate());
                            int childSize = datas.get(i).getInfo().size();
                            for (int j = 0; j < childSize; j++) {
                                GiftRespBean.GiftBean.InfoBean info = datas.get(i).getInfo().get(j);
                                LinearLayout llChild = (LinearLayout) mInflater.inflate(R.layout.gift_item_child_layout, null);
                                TextView giftName = (TextView) llChild.findViewById(R.id.tv_giftname);
                                TextView nickName = (TextView) llChild.findViewById(R.id.tv_nickname);
                                TextView giftNum = (TextView) llChild.findViewById(R.id.tv_giftcount);
                                ImageView imgGift = (ImageView) llChild.findViewById(R.id.img_gifticon);
                                giftName.setText(info.getGiftname());
                                nickName.setText(info.getNickname());
                                giftNum.setText(String.valueOf(info.getGiftnum()));
                                x.image().bind(imgGift,
                                        info.getGifticon(),
                                        imageOptions,
                                        null);
                                llChildContainer.addView(llChild);
                            }
                            llMaxContainer.addView(llParentContainer);
                        }
                    } else {
                        showToast("暂时无数据");
                    }
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
