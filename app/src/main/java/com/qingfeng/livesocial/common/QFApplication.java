package com.qingfeng.livesocial.common;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/8/21.
 */

public class QFApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils初始化
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
