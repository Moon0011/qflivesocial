package com.qingfeng.livesocial.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.PersonalCenterRespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.UIHelper;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.AnchorAnthenActivity;
import com.qingfeng.livesocial.ui.AnchorInfoActivity;
import com.qingfeng.livesocial.ui.AttentionActivity;
import com.qingfeng.livesocial.ui.CallSettingActivity;
import com.qingfeng.livesocial.ui.GiftActivity;
import com.qingfeng.livesocial.ui.MyWalletActivity;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 */

public class PersonalCenterFragment extends BaseFragment {
    @Bind(R.id.img_head)
    RoundedImageView imgHead;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_curr_roomnum)
    TextView tvCurrRoomnum;
    @Bind(R.id.img_sex)
    ImageView imgSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_signature)
    TextView tvSignature;
    @Bind(R.id.img_room_bg)
    ImageView imgRoomBg;

    @Override
    protected int getLayoutId() {
        return R.layout.personal_center_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {
        getPersonalCenter();
    }

    @OnClick({R.id.rl_wallet, R.id.rl_gift, R.id.rl_attention, R.id.rl_anchor_anthen, R.id.rl_setting, R.id.ll_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_wallet://钱包
                gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.rl_gift://礼物
                gotoActivity(getActivity(), GiftActivity.class);
                break;
            case R.id.rl_attention://关注
                gotoActivity(getActivity(), AttentionActivity.class);
                break;
            case R.id.rl_anchor_anthen://主播认证
                gotoActivity(getActivity(), AnchorAnthenActivity.class);
                break;
            case R.id.rl_setting://通话设置
                gotoActivity(getActivity(), CallSettingActivity.class);
                break;
            case R.id.ll_data://资料编辑
                gotoActivity(getActivity(), AnchorInfoActivity.class);
                break;
        }
    }

    private void getPersonalCenter() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getPersonalCenter == " + result);
                PersonalCenterRespBean respBean = new Gson().fromJson(result, PersonalCenterRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    PersonalCenterRespBean.PersonalCenterBean bean = respBean.getResult();
                    if (bean != null) {
                        tvNickname.setText(bean.getNickname());
                        tvAge.setText(bean.getAge());
                        tvSignature.setText(bean.getSignature());
                        tvCurrRoomnum.setText(String.valueOf(bean.getCurroomnum()));
                        UIHelper.setSexLabel(bean.getSex(), imgSex);
                        x.image().bind(imgHead,
                                bean.getAnchorpic(),
                                imageOptions,
                                null);
                        x.image().bind(imgRoomBg,
                                bean.getRoompic(),
                                imageOptions,
                                null);
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
}
