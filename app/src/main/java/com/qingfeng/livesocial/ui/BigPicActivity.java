package com.qingfeng.livesocial.ui;

import android.widget.ImageView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.StringUtils;

import org.xutils.x;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by hover on 2017/9/2.
 */

public class BigPicActivity extends BaseActivity {
    @Bind(R.id.photoView)
    PhotoView mPhotoView;
    private String picUrl;

    @Override
    protected int getLayoutById() {
        return R.layout.big_pic_layout2;
    }

    @Override
    protected void initView() {
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER);
    }

    @Override
    protected void initData() {
        picUrl = getIntent().getExtras().getString("picurl");
        if (!StringUtils.isEmpty(picUrl)) {
            x.image().bind(mPhotoView,
                    picUrl,
                    imageOptions,
                    null);
        }
    }

}
