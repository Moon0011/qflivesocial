package com.qingfeng.livesocial.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.CurLiveInfo;
import com.qingfeng.livesocial.bean.LiveInfoJson;
import com.qingfeng.livesocial.bean.MemberID;
import com.qingfeng.livesocial.bean.MySelfInfo;
import com.qingfeng.livesocial.common.Constants;
import com.qingfeng.livesocial.live.GetLinkSignHelper;
import com.qingfeng.livesocial.live.LiveHelper;
import com.qingfeng.livesocial.live.UserServerHelper;
import com.qingfeng.livesocial.live.viewinface.GetLinkSigView;
import com.qingfeng.livesocial.live.viewinface.LiveView;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.SxbLog;
import com.squareup.picasso.Picasso;
import com.tencent.TIMMessage;
import com.tencent.av.extra.effect.AVVideoEffect;
import com.tencent.av.opengl.ui.GLView;
import com.tencent.av.sdk.AVVideoCtrl;
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

import static com.tencent.qalsdk.service.QalService.context;


/**
 * Live直播类
 */
public class LiveActivity extends BaseActivity implements LiveView, GetLinkSigView {
    private static final String TAG = LiveActivity.class.getSimpleName();
    private static final int GETPROFILE_JOIN = 0x200;
    @Bind(R.id.av_root_view)
    AVRootView mRootView;
    @Bind(R.id.head_icon)
    ImageView mHeadIcon;
    @Bind(R.id.broadcasting_time)
    TextView mVideoTime;
    @Bind(R.id.host_name)
    TextView mHostNameTv;
    @Bind(R.id.room_id)
    TextView roomId;
    @Bind(R.id.controll_ui)
    FrameLayout mFullControllerUi;
    @Bind(R.id.ll_initiate_call)
    LinearLayout llInitiateCall;
    @Bind(R.id.ll_answer_call)
    LinearLayout llAnswerCall;
    @Bind(R.id.et_hostid)
    EditText etHostid;

    private LiveHelper mLiveHelper;
    private GetLinkSignHelper mLinkHelper;
    private static final int UPDAT_WALL_TIME_TIMER_TASK = 1;
    private long mSecond = 0;
    private String formatTime;
    private Timer mHearBeatTimer, mVideoTimer;
    private VideoTimerTask mVideoTimerTask;//计时器
    private boolean bInAvRoom = false;
    private String receiveID;


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDAT_WALL_TIME_TIMER_TASK:
                    updateWallTime();
                    break;
            }
            return false;
        }
    });

    @OnClick({R.id.link_btn, R.id.img_refuse, R.id.img_answer, R.id.img_cancel, R.id.img_camera, R.id.img_calling_cancel, R.id.img_gift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.link_btn:
                llInitiateCall.setVisibility(View.VISIBLE);
                List<String> curLinkedList = ILVLiveManager.getInstance().getCurrentLinkedUserArray();
                if (null != curLinkedList && curLinkedList.size() >= 1) {
                    Toast.makeText(LiveActivity.this, getString(R.string.str_tips_link_limit), Toast.LENGTH_SHORT).show();
                } else {
                    mLiveHelper.sendLinkReq(etHostid.getText().toString().trim());
                }
                break;
            case R.id.img_refuse:
                mLiveHelper.refuseLink(receiveID);
                llAnswerCall.setVisibility(View.GONE);
                finish();
                break;
            case R.id.img_answer:
                mLiveHelper.acceptLink(receiveID);
                llAnswerCall.setVisibility(View.GONE);
                break;
            case R.id.img_cancel:
                finish();
                break;
            case R.id.img_camera:
                break;
            case R.id.img_calling_cancel:
                if (bInAvRoom) {
                    quiteLiveByPurpose();
                    finish();
                }
                break;
            case R.id.img_gift:
                break;
        }
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
            Bitmap cirBitMap = createCircleImage(bitmap, 0);
            view.setImageBitmap(cirBitMap);
        } else {
            Picasso.with(context)
                    .load(avatar)
                    .into(view);
        }
    }

    private static Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (0 == min) {
            min = source.getHeight() > source.getWidth() ? source.getWidth() : source.getHeight();
        }
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        // 创建画布
        Canvas canvas = new Canvas(target);
        // 绘圆
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        // 设置交叉模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 绘制图片
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    @Override
    protected int getLayoutById() {
        return R.layout.activity_live;
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLiveHelper = new LiveHelper(this, this);
        mLinkHelper = new GetLinkSignHelper(this);
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            View view = findViewById(R.id.link_btn);
            view.setVisibility(View.VISIBLE);
            showHeadIcon(mHeadIcon, MySelfInfo.getInstance().getAvatar());
        }
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
        //进入房间流程
        mLiveHelper.startEnterRoom();
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
        if (null != mHearBeatTimer) {
            mHearBeatTimer.cancel();
            mHearBeatTimer = null;
        }
        if (null != mVideoTimer) {
            mVideoTimer.cancel();
            mVideoTimer = null;
        }
        CurLiveInfo.setMembers(0);
        CurLiveInfo.setAdmires(0);
        CurLiveInfo.setCurrentRequestCount(0);
        mLiveHelper.onDestory();
    }

    @Override
    public void forceQuitRoom(String strMessage) {
        if (isDestroyed() || isFinishing()) {
            return;
        }
        ILiveRoomManager.getInstance().onPause();
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(strMessage)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create();
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                callExitRoom();
            }
        });
        alertDialog.show();
    }

    @Override
    public void refuseLink(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

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
        mRootView.getViewByIndex(0).setVisibility(GLView.VISIBLE);
        bInAvRoom = true;
        roomId.setText("" + CurLiveInfo.getRoomNum());
        if (isSucc == true) {
            //直播时间
            mVideoTimer = new Timer(true);
            mVideoTimerTask = new VideoTimerTask();
            mVideoTimer.schedule(mVideoTimerTask, 1000, 1000);
            initPtuEnv();
            //IM初始化
            if (id_status == Constants.HOST) {//主播方式加入房间成功
                mHostNameTv.setText(MySelfInfo.getInstance().getId());
                //开启摄像头渲染画面
                SxbLog.i(TAG, "createlive enterRoomComplete isSucc" + isSucc);
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putBoolean("living", true);
                editor.apply();
            }
        }
    }

    @Override
    public void quiteRoomComplete(int id_status, boolean succ, LiveInfoJson liveinfo) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                UserServerHelper.getInstance().reportMe(MySelfInfo.getInstance().getIdStatus(), 1);//通知server 我下线了
            }
        }.start();
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            if ((getBaseContext() != null)) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putBoolean("living", false);
                editor.apply();
            }
        }
        //发送
        bInAvRoom = false;
    }

    @Override
    public void linkRoomReq(final String id, String name) {
        llAnswerCall.setVisibility(View.VISIBLE);
        receiveID = id;
    }

    @Override
    public void linkRoomAccept(final String id, final String strRoomId) {
        mLinkHelper.getLinkSign(id, strRoomId);
        llInitiateCall.setVisibility(View.GONE);
        Toast.makeText(this, "已连接上对方,开始视频通话", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetSignRsp(String id, String roomid, String sign) {
        SxbLog.d(TAG, "onGetSignRsp->id:" + id + ", room:" + roomid + ", sign:" + sign);
        mLiveHelper.linkRoom(id, roomid, sign);
    }

    @Override
    public void changeCtrlView(boolean videoMember) {
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            // 主播不存在切换
            return;
        }
    }

    @Override
    public void onBackPressed() {
        if (bInAvRoom) {
            quiteLiveByPurpose();
            finish();
        }
    }

    @Override
    public void hostLeave(String id, String name) {
    }

    @Override
    public void hostBack(String id, String name) {
    }

    @Override
    public void refreshMember(ArrayList<MemberID> memlist) {
    }

    @Override
    public void showInviteDialog() {
    }

    @Override
    public void hideInviteDialog() {
    }

    @Override
    public void refreshText(String text, String name) {
    }

    @Override
    public void refreshThumbUp() {
    }

    @Override
    public void cancelInviteView(String id) {
    }

    @Override
    public void cancelMemberView(String id) {
    }

    @Override
    public void memberJoin(String id, String name) {

    }

    @Override
    public boolean showInviteView(String id) {
        return false;
    }
}
