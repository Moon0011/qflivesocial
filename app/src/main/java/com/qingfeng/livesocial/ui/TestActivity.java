package com.qingfeng.livesocial.ui;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;

/**
 * Created by hover on 2017/8/21.
 */

public class TestActivity extends BaseActivity {
    @Override
    protected int getLayoutById() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(slide);
        getWindow().setEnterTransition(slide);
        return R.layout.test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
