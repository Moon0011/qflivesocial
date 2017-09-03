package com.qingfeng.livesocial.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.common.AppManager;
import com.qingfeng.livesocial.util.StringUtils;
import com.qingfeng.livesocial.widget.progressbar.SVProgressHUD;

import org.xutils.image.ImageOptions;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected ImageOptions imageOptions;

    protected abstract int getLayoutById();

    protected abstract void initView();

    protected abstract void initData();

    protected void gotoActivity(Activity activity, Class nextActivity) {
        startActivity(new Intent(activity, nextActivity));
    }

    protected void gotoActivityWithBundle(Activity activity, Class nextActivity, Bundle bundle) {
        Intent intent = new Intent(activity, nextActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (getLayoutById() != 0) {
            setContentView(getLayoutById());
        }
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
        imageOptions = new ImageOptions.Builder()
                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(R.mipmap.error_pic)
                .setFailureDrawableId(R.mipmap.error_pic)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    protected void showToast(String msg) {
        if (!StringUtils.isEmpty(msg)) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showProgress() {
        SVProgressHUD.show(mContext);
    }

    protected void dismissProgress() {
        SVProgressHUD.dismiss(mContext);
    }
}
