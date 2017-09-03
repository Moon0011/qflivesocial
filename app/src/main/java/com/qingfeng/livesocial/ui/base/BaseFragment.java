package com.qingfeng.livesocial.ui.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.util.StringUtils;

import org.xutils.image.ImageOptions;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRoot;

    protected ImageOptions imageOptions;

    protected abstract int getLayoutId();

    protected abstract void initWidget(View root);

    protected abstract void initData();

    protected void showToast(String msg) {
        if (!StringUtils.isEmpty(msg)) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void gotoActivity(Activity activity, Class nextActivity) {
        startActivity(new Intent(activity, nextActivity));
    }

    protected void gotoActivityWithBundle(Activity activity, Class nextActivity, Bundle bundle) {
        Intent intent = new Intent(activity, nextActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRoot);
        initWidget(mRoot);
        initData();
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageOptions = new ImageOptions.Builder()
                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(R.mipmap.error_pic)
                .setFailureDrawableId(R.mipmap.error_pic)
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
