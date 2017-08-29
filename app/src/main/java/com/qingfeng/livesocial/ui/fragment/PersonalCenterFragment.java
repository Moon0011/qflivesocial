package com.qingfeng.livesocial.ui.fragment;

import android.view.View;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.AnchorDataActivity;
import com.qingfeng.livesocial.ui.AnchorAnthenActivity;
import com.qingfeng.livesocial.ui.AttentionActivity;
import com.qingfeng.livesocial.ui.CallSettingActivity;
import com.qingfeng.livesocial.ui.GiftActivity;
import com.qingfeng.livesocial.ui.MyWalletActivity;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/29.
 */

public class PersonalCenterFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.personal_center_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.rl_wallet, R.id.rl_gift, R.id.rl_attention, R.id.rl_anchor_anthen, R.id.rl_setting, R.id.ll_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_wallet:
                gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.rl_gift:
                gotoActivity(getActivity(), GiftActivity.class);
                break;
            case R.id.rl_attention:
                gotoActivity(getActivity(), AttentionActivity.class);
                break;
            case R.id.rl_anchor_anthen:
                gotoActivity(getActivity(), AnchorAnthenActivity.class);
                break;
            case R.id.rl_setting:
                gotoActivity(getActivity(), CallSettingActivity.class);
                break;
            case R.id.ll_data:
                gotoActivity(getActivity(), AnchorDataActivity.class);
                break;
        }
    }

}
