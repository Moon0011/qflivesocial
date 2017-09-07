package com.qingfeng.livesocial.common;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.qingfeng.livesocial.bean.UserInfoBean;
import com.qingfeng.livesocial.live.InitBusinessHelper;
import com.qingfeng.livesocial.util.StringUtils;

import org.xutils.x;

import java.util.List;
import java.util.Properties;


/**
 * Created by Administrator on 2017/8/21.
 */

public class QFApplication extends Application {
    private static QFApplication instance;
    public boolean login = false;
    static Context _context;

    public static QFApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        instance = this;
        init_xUtil();
        initLogin();
        initLiveSdk();
    }

    public static synchronized QFApplication context() {
        return (QFApplication) _context;
    }

    public static void showToast(String msg) {
        Toast.makeText(_context, msg, Toast.LENGTH_SHORT).show();
    }

    private void init_xUtil() {
        x.Ext.init(this);//继承MultiDexApplication解决部分机型xUtil初始化问题
        x.Ext.setDebug(true);
    }

    private void initLogin() {
        UserInfoBean userInfo = getLoginUser();
        if (null != userInfo && userInfo.getUid() > 0) {
            login = true;
        } else {
            this.cleanLoginInfo();
        }
    }

    private void initLiveSdk() {
        if (shouldInit()) {
            InitBusinessHelper.initApp(_context);
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();

        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }


    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 保存登录信息
     */
    @SuppressWarnings("serial")
    public void saveUserInfo(final UserInfoBean userInfo) {
        this.login = true;
        setProperties(new Properties() {
            {
                setProperty("user.uid", String.valueOf(userInfo.getUid()));
                setProperty("user.username", userInfo.getUsername());
                setProperty("user.sign", userInfo.getSign());
                setProperty("user.isLogin",
                        String.valueOf(userInfo.getIslogin()));
            }
        });
    }

    /**
     * 更新用户信息
     */
    @SuppressWarnings("serial")
    public void updateUserInfo(final UserInfoBean userInfoBean) {
        setProperties(new Properties() {
            {
                setProperty("user.username", userInfoBean.getUsername());
//                setProperty("user.face", userInfoBean.getHeadimg());// 用户头像-文件名

            }
        });
    }

    /**
     * 获得登录用户的信息
     *
     * @return
     */
    public UserInfoBean getLoginUser() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUid(StringUtils.toInt(getProperty("user.uid"), 0));
        userInfoBean.setUsername(getProperty("user.username"));
        userInfoBean.setSign(getProperty("user.sign"));
        userInfoBean.setIslogin(StringUtils.toInt(getProperty("user.isLogin")));
        return userInfoBean;
    }

    /**
     * 清除登录信息
     */
    public void cleanLoginInfo() {
        this.login = false;
        removeProperty("user.uid", "user.account", "user.face", "user.pwd",
                "user.isLogin");
    }
}
