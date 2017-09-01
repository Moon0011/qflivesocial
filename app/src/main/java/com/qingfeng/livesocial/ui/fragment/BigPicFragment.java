package com.qingfeng.livesocial.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingfeng.livesocial.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/9/1.
 */

public class BigPicFragment extends DialogFragment {
    private PhotoView photoView;
    private String picUrl;
    private ImageOptions mImageOptions;

    public BigPicFragment(String url, ImageOptions imageOptions) {
        this.picUrl = url;
        this.mImageOptions = imageOptions;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
//        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.big_pic_layout, container);
        photoView = (PhotoView) view.findViewById(R.id.photoView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        x.image().bind(photoView,
                picUrl,
                mImageOptions,
                null);
    }
}
