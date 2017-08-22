package com.qingfeng.livesocial.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22.
 */

public class RegistInfoActivity extends BaseActivity {
    @Bind(R.id.img_photo_add)
    ImageView imgPhotoAdd;
    @Bind(R.id.rl_regist_succ)
    RelativeLayout rlRegistSucc;

    @Override
    protected int getLayoutById() {
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
//        getWindow().setExitTransition(transition);
//        getWindow().setEnterTransition(transition);
        return R.layout.regist_info_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_photo_add, R.id.rl_regist_succ, R.id.img_arrow_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_photo_add:
                break;
            case R.id.rl_regist_succ:
                gotoActivity(RegistInfoActivity.this, RecommendActivity.class);
                break;
            case R.id.img_arrow_left:
                finish();
                break;
        }
    }

}
