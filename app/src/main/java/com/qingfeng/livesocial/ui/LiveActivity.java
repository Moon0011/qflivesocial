package com.qingfeng.livesocial.ui;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.LiveInfoJson;
import com.qingfeng.livesocial.bean.MemberID;
import com.qingfeng.livesocial.bean.MySelfInfo;
import com.qingfeng.livesocial.common.Constants;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.live.LiveHelper;
import com.qingfeng.livesocial.live.viewinface.LiveView;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.SxbLog;
import com.qingfeng.livesocial.util.UIUtils;
import com.tencent.TIMMessage;
import com.tencent.av.extra.effect.AVVideoEffect;
import com.tencent.av.opengl.ui.GLView;
import com.tencent.av.sdk.AVVideoCtrl;
import com.tencent.av.sdk.AVView;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.ilivesdk.view.AVVideoView;
import com.tencent.livesdk.ILVCustomCmd;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.livesdk.ILVText;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 互动直播
 */
public class LiveActivity extends BaseActivity implements LiveView {
    private static final String TAG = LiveActivity.class.getSimpleName();
    @Bind(R.id.av_root_view)
    AVRootView mRootView;
    @Bind(R.id.host_name)
    TextView mHostNameTv;
    @Bind(R.id.broadcasting_time)
    TextView mVideoTime;
    @Bind(R.id.head_icon)
    ImageView mHeadIcon;
    @Bind(R.id.room_id)
    TextView roomID;
    @Bind(R.id.ll_initiate_call)
    LinearLayout llInitiateCall;
    @Bind(R.id.ll_answer_call)
    LinearLayout llAnswerCall;

    private LiveHelper mLiveHelper;
    private static final int UPDAT_WALL_TIME_TIMER_TASK = 1;
    private static final int TIMEOUT_INVITE = 2;
    private boolean mBoolRefreshLock = false;
    private static final int REFRESH_LISTVIEW = 5;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private long mSecond = 0;
    private String formatTime;
    private Timer mVideoTimer;
    private VideoTimerTask mVideoTimerTask;//计时器
    private boolean bInAvRoom = false;
    private boolean bVideoMember = false;       // 是否上麦观众
    private String hostId;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDAT_WALL_TIME_TIMER_TASK:
                    updateWallTime();
                    break;
                case REFRESH_LISTVIEW:
//                    doRefreshListView();
                    break;
                case TIMEOUT_INVITE:
                    String id = "" + msg.obj;
                    cancelInviteView(id);
                    mLiveHelper.sendGroupCmd(Constants.AVIMCMD_MULTI_HOST_CANCELINVITE, id);
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutById() {
        return R.layout.activity_live;
    }

    @Override
    protected void initView() {
        hostId = getIntent().getExtras().getString(Constants.PARAM_HOST_ID);
        Constants.hostId = hostId;
        checkPermission();
        mLiveHelper = new LiveHelper(this, this);
        //进入房间流程
        mLiveHelper.startEnterRoom();
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            // 初始化主播控件
            showHeadIcon(mHeadIcon, MySelfInfo.getInstance().getAvatar());
        } else {
            // 初始化观众控件
            mHostNameTv.setVisibility(View.VISIBLE);
            showHeadIcon(mHeadIcon, "");
            mHostNameTv.setText(UIUtils.getLimitString(Constants.hostId, 10));
        }
        // 设置渲染层
        ILVLiveManager.getInstance().setAvVideoView(mRootView);
        mRootView.setBackground(R.mipmap.renderback);
        mRootView.setGravity(AVRootView.LAYOUT_GRAVITY_RIGHT);
        mRootView.setSubMarginY(getResources().getDimensionPixelSize(R.dimen.small_area_margin_top));
        mRootView.setSubMarginX(getResources().getDimensionPixelSize(R.dimen.small_area_marginright));
        mRootView.setSubPadding(getResources().getDimensionPixelSize(R.dimen.small_area_marginbetween));
        mRootView.setSubWidth(getResources().getDimensionPixelSize(R.dimen.small_area_width));
        mRootView.setSubHeight(getResources().getDimensionPixelSize(R.dimen.small_area_height));
        mRootView.setSubCreatedListener(new AVRootView.onSubViewCreatedListener() {
            @Override
            public void onSubViewCreated() {
                for (int i = 1; i < ILiveConstants.MAX_AV_VIDEO_NUM; i++) {
                    final int index = i;
                    AVVideoView avVideoView = mRootView.getViewByIndex(index);
                    avVideoView.setRotate(false);
                    avVideoView.setGestureListener(new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapConfirmed(MotionEvent e) {
                            mRootView.swapVideoView(0, index);
                            return super.onSingleTapConfirmed(e);
                        }
                    });
                }
                mRootView.getViewByIndex(0).setRotate(false);
                mRootView.getViewByIndex(0).setBackground(R.mipmap.renderback);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.img_refuse, R.id.img_answer, R.id.img_calling_cancel, R.id.img_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_refuse://拒绝
                mLiveHelper.sendC2CCmd(Constants.AVIMCMD_MUlTI_REFUSE, "", hostId);
                quiteLiveByPurpose();
                finish();
                break;
            case R.id.img_answer://接听
                mLiveHelper.sendC2CCmd(Constants.AVIMCMD_MUlTI_JOIN, "", hostId);
                mLiveHelper.upMemberVideo();//切换为连麦观众
                break;
            case R.id.img_calling_cancel://挂断
                if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
                    mLiveHelper.sendGroupCmd(Constants.AVIMCMD_MULTI_CANCEL_INTERACT, MySelfInfo.getInstance().getId());
                    mRootView.closeUserView(MySelfInfo.getInstance().getId(), AVView.VIDEO_SRC_TYPE_CAMERA, true);
                } else {
                    mLiveHelper.sendGroupCmd(Constants.AVIMCMD_MULTI_CANCEL_INTERACT, hostId);
                }
                quiteLiveByPurpose();
                finish();
                break;
            case R.id.img_cancel://取消
                quiteLiveByPurpose();
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ILiveRoomManager.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ILiveRoomManager.getInstance().onPause();
    }

    private void updateWallTime() {
        String hs, ms, ss;
        long h, m, s;
        h = mSecond / 3600;
        m = (mSecond % 3600) / 60;
        s = (mSecond % 3600) % 60;
        if (h < 10) {
            hs = "0" + h;
        } else {
            hs = "" + h;
        }
        if (m < 10) {
            ms = "0" + m;
        } else {
            ms = "" + m;
        }
        if (s < 10) {
            ss = "0" + s;
        } else {
            ss = "" + s;
        }
        if (hs.equals("00")) {
            formatTime = ms + ":" + ss;
        } else {
            formatTime = hs + ":" + ms + ":" + ss;
        }
        if (Constants.HOST == MySelfInfo.getInstance().getIdStatus() && null != mVideoTime) {
            SxbLog.i(TAG, " refresh time ");
            mVideoTime.setText(formatTime);
        }
    }

    private void showHeadIcon(ImageView view, String avatar) {
        if (TextUtils.isEmpty(avatar)) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.f_head);
            Bitmap cirBitMap = UIUtils.createCircleImage(bitmap, 0);
            view.setImageBitmap(cirBitMap);
        }
//        else {
//            SxbLog.d(TAG, "load icon: " + avatar);
//            RequestManager req = Glide.with(this);
//            req.load(avatar).transform(new GlideCircleTransform(this)).into(view);
//        }
    }


    private class VideoTimerTask extends TimerTask {
        public void run() {
            SxbLog.i(TAG, "timeTask ");
            ++mSecond;
            if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST)
                mHandler.sendEmptyMessage(UPDAT_WALL_TIME_TIMER_TASK);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mVideoTimer) {
            mVideoTimer.cancel();
            mVideoTimer = null;
        }
        mLiveHelper.onDestory();
    }

    @Override
    public void onBackPressed() {
        if (bInAvRoom) {
            quiteLiveByPurpose();

        } else {
            clearOldData();
            finish();
        }
    }

    @Override
    public void forceQuitRoom(String strMessage) {
        if (isDestroyed() || isFinishing()) {
            return;
        }
        ILiveRoomManager.getInstance().onPause();
        callExitRoom();
    }

    @Override
    public void refuseLink(String msg) {
        Log.e("live", "refuseLink()");
    }

    @Override
    public void unlinkRoom() {
        Log.e("live", "unlinkRoom()");
    }

    @Override
    public void callingHangup(String id) {
        Log.e("live", "callingHangup()");
        quiteLiveByPurpose();
        finish();
    }

    /**
     * 主动退出直播
     */
    private void quiteLiveByPurpose() {
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            ILVCustomCmd cmd = new ILVCustomCmd();
            cmd.setCmd(Constants.AVIMCMD_EXITLIVE);
            cmd.setType(ILVText.ILVTextType.eGroupMsg);
            ILVLiveManager.getInstance().sendCustomCmd(cmd, new ILiveCallBack<TIMMessage>() {
                @Override
                public void onSuccess(TIMMessage data) {
                    //如果是直播，发消息
                    if (null != mLiveHelper) {
                        callExitRoom();
                    }
                }

                @Override
                public void onError(String module, int errCode, String errMsg) {
                }

            });
            // 取消跨房连麦
            ILVLiveManager.getInstance().unlinkRoom(null);
        } else {
            callExitRoom();
        }
    }

    private void callExitRoom() {
        mLiveHelper.startExitRoom();
    }

    private void initPtuEnv() {
        AVVideoEffect mEffect = AVVideoEffect.getInstance(LiveActivity.this);
        AVVideoCtrl avVideoCtrl = ILiveSDK.getInstance().getAvVideoCtrl();
        if (null != avVideoCtrl)
            avVideoCtrl.setEffect(mEffect);
    }

    /**
     * 完成进出房间流程
     */
    @Override
    public void enterRoomComplete(int id_status, boolean isSucc) {
        roomID.setText(String.valueOf(QFApplication.getInstance().getLoginUser().getCurroomnum()));
        mRootView.getViewByIndex(0).setVisibility(GLView.VISIBLE);
        //mRootView.getViewByIndex(0).setRotate(true);
//        mRootView.getViewByIndex(0).setDiffDirectionRenderMode(AVVideoView.ILiveRenderMode.BLACK_TO_FILL);
        bInAvRoom = true;
        if (isSucc == true) {
            //直播时间
            mVideoTimer = new Timer(true);
            mVideoTimerTask = new VideoTimerTask();
            mVideoTimer.schedule(mVideoTimerTask, 1000, 1000);
            initPtuEnv();
            //IM初始化
            if (id_status == Constants.HOST) {//主播方式加入房间成功
                mHostNameTv.setText(QFApplication.getInstance().getLoginUser().getUsername());
                //开启摄像头渲染画面
                SxbLog.i(TAG, "createlive enterRoomComplete isSucc" + isSucc);
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putBoolean("living", true);
                editor.apply();

            } else {
                //发消息通知上线
                mLiveHelper.sendGroupCmd(Constants.AVIMCMD_ENTERLIVE, "");
            }
        }
    }

    @Override
    public void quiteRoomComplete(int id_status, boolean succ, LiveInfoJson liveinfo) {
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
//            if ((getBaseContext() != null) && (null != mDetailDialog) && (mDetailDialog.isShowing() == false)) {
//
//                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
//                editor.putBoolean("living", false);
//                editor.apply();
//                mDetailDialog.show();
//            }
        } else {
            clearOldData();
            finish();
        }
        //发送
        bInAvRoom = false;
    }

    @Override
    public void memberJoin(String id, String name) {
        Log.e("live", "memberJoin()");
        Toast.makeText(this, "join succ", Toast.LENGTH_SHORT).show();
        llInitiateCall.setVisibility(View.VISIBLE);
        mLiveHelper.sendC2CCmd(Constants.AVIMCMD_MUlTI_HOST_INVITE, "", id);
    }

    @Override
    public void hostLeave(String id, String name) {
        Log.e("live", "hostLeave()");
    }

    @Override
    public void hostBack(String id, String name) {
        Log.e("live", "hostBack()");
    }

    @Override
    public void refreshMember(ArrayList<MemberID> memlist) {
        Log.e("live", "refreshMember()");
    }

    @Override
    public void linkRoomReq(final String id, String name) {
    }

    @Override
    public void linkRoomAccept(final String id, final String strRoomId) {
    }

    @Override
    public void showInviteDialog() {
//        if ((inviteDg != null) && (getBaseContext() != null) && (inviteDg.isShowing() != true)) {
//            inviteDg.show();
//        }
        llAnswerCall.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInviteDialog() {
        Log.e("live", "hideInviteDialog()");
    }

    @Override
    public void refreshText(String text, String name) {
        Log.e("live", "refreshText()");
    }

    @Override
    public void refreshThumbUp() {
    }

    @Override
    public void cancelInviteView(String id) {
        Log.e("live", "cancelInviteView()");
        llInitiateCall.setVisibility(View.GONE);
        quiteLiveByPurpose();
        finish();
    }

    @Override
    public void succInviteView(String id) {
        llInitiateCall.setVisibility(View.GONE);
    }

    @Override
    public void agreeAnswer() {
        llAnswerCall.setVisibility(View.GONE);
    }

    @Override
    public void cancelMemberView(String id) {
    }

    @Override
    public void changeCtrlView(boolean videoMember) {

    }

    @Override
    public boolean showInviteView(String id) {
//        mLiveHelper.sendC2CCmd(Constants.AVIMCMD_MUlTI_HOST_INVITE, "", id);
//        //30s超时取消
//        Message msg = new Message();
//        msg.what = TIMEOUT_INVITE;
//        msg.obj = id;
//        mHandler.sendMessageDelayed(msg, 30 * 1000);
        return false;
    }

    void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.CAMERA);
            if ((checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.RECORD_AUDIO);
            if ((checkSelfPermission(Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WAKE_LOCK);
            if ((checkSelfPermission(Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
            if (permissionsList.size() != 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PHONE_PERMISSIONS);
            }
        }
    }

    // 清除老房间数据
    private void clearOldData() {
        if (mBoolRefreshLock) {
            return;
        } else {
        }
        mRootView.clearUserView();
    }
}
