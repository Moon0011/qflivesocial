package com.qingfeng.livesocial.ui.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRoot;

    protected abstract int getLayoutId();

    protected abstract void initWidget(View root);

    protected abstract void initData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (mRoot != null) {
//            ViewGroup parent = (ViewGroup) mRoot.getParent();
//            if (parent != null)
//                parent.removeView(mRoot);
//        } else {
//            mRoot = inflater.inflate(getLayoutId(), container, false);
//            ButterKnife.bind(this, mRoot);
//            initWidget(mRoot);
//            initData();
//        }
        mRoot = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRoot);
        initWidget(mRoot);
        initData();
        return mRoot;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
