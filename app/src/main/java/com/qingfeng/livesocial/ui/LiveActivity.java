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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.SendGiftAdapter2;
import com.qingfeng.livesocial.bean.CurLiveInfo;
import com.qingfeng.livesocial.bean.LiveInfoJson;
import com.qingfeng.livesocial.bean.MemberID;
import com.qingfeng.livesocial.bean.SendGiftListRespBean;
import com.qingfeng.livesocial.common.Constants;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.live.GetLinkSignHelper;
import com.qingfeng.livesocial.live.LiveHelper;
import com.qingfeng.livesocial.live.viewinface.GetLinkSigView;
import com.qingfeng.livesocial.live.viewinface.LiveView;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.SxbLog;
import com.qingfeng.livesocial.widget.pagescroll.PagingScrollHelper;
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

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_UID;


/**
 * Live直播
 */
public class LiveActivity extends BaseActivity implements LiveView, GetLinkSigView, PagingScrollHelper.onPageChangeListener {
    private static final String TAG = LiveActivity.class.getSimpleName();
    @Bind(R.id.av_root_view)
    AVRootView mRootView;
    @Bind(R.id.head_icon)
    ImageView mHeadIcon;
    @Bind(R.id.broadcasting_time)
    TextView mVideoTime;
    @Bind(R.id.host_name)
    TextView mHostNameTv;
    @Bind(R.id.ll_initiate_call)
    LinearLayout llInitiateCall;
    @Bind(R.id.ll_answer_call)
    LinearLayout llAnswerCall;
    @Bind(R.id.et_hostid)
    EditText etHostid;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.ll_gift)
    LinearLayout llGift;
    @Bind(R.id.dotLayout)
    LinearLayout dotLayout;

    private LiveHelper mLiveHelper;
    private GetLinkSignHelper mLinkHelper;
    private static final int UPDAT_WALL_TIME_TIMER_TASK = 1;
    private long mSecond = 0;
    private String formatTime;
    private Timer mHearBeatTimer, mVideoTimer;
    private VideoTimerTask mVideoTimerTask;//计时器
    private boolean bInAvRoom = false;
    private String receiveID;
    private PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private List<View> dotViewsList = new ArrayList<View>();

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


    @Override
    protected int getLayoutById() {
        return R.layout.activity_live;
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLiveHelper = new LiveHelper(this, this);
        mLinkHelper = new GetLinkSignHelper(this);
        View view = findViewById(R.id.link_btn);
        view.setVisibility(View.VISIBLE);
        showHeadIcon(mHeadIcon, "");
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
        mLiveHelper.startEnterRoom();
    }


    @OnClick({R.id.link_btn, R.id.img_refuse, R.id.img_answer, R.id.img_cancel, R.id.img_camera,
            R.id.img_calling_cancel, R.id.img_gift, R.id.btn_sendgift})
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
                ILiveRoomManager.getInstance().switchCamera(1 - ILiveRoomManager.getInstance().getCurCameraId());
                break;
            case R.id.img_calling_cancel:
                if (bInAvRoom) {
                    quiteLiveByPurpose();
                    finish();
                }
                break;
            case R.id.img_gift:
                sendGiftList();
                break;
            case R.id.btn_sendgift:
                llGift.setVisibility(View.GONE);
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
        if (null != mVideoTime) {
            LogUtil.e(" refresh time ");
            mVideoTime.setText(formatTime);
        }
    }

    private void showHeadIcon(ImageView view, String avatar) {
        if (TextUtils.isEmpty(avatar)) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.f_head);
            Bitmap cirBitMap = createCircleImage(bitmap, 0);
            view.setImageBitmap(cirBitMap);
        } else {
            Glide.with(mContext).load(avatar).error(R.mipmap.error_pic).into(view);
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
            LogUtil.e("timeTask ");
            ++mSecond;
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

    @Override
    public void unlinkroom() {
        quiteLiveByPurpose();
        finish();
    }

    private void quiteLiveByPurpose() {
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
                LogUtil.e("createlive enterRoomComplete isSucc" + isSucc);
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putBoolean("living", true);
                editor.apply();
            }
        }
    }

    @Override
    public void quiteRoomComplete(int id_status, boolean succ, LiveInfoJson liveinfo) {
        if ((getBaseContext() != null)) {
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putBoolean("living", false);
            editor.apply();
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
    public void onBackPressed() {
        if (llGift.getVisibility() == View.VISIBLE) {
            llGift.setVisibility(View.GONE);
        } else if (bInAvRoom) {
            quiteLiveByPurpose();
            finish();
        } else {
            finish();
        }
    }

    private void sendGiftList() {
        RequestParams params = new RequestParams(Urls.SEND_GIFT_LIST);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("sendGiftList == " + result);
                Gson gson = new Gson();
                final SendGiftListRespBean bean = gson.fromJson(result, SendGiftListRespBean.class);

                int giftCount = bean.getResult().getGiftinfo().size();
                int totalPager = giftCount % 8 == 0 ? giftCount / 8 : giftCount / 8 + 1;
                dotLayout.removeAllViews();
                dotViewsList.clear();
                for (int i = 0; i < totalPager; i++) {
                    ImageView dotView = new ImageView(mContext);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                    params.leftMargin = 4;
                    params.rightMargin = 4;
                    params.gravity = Gravity.CENTER;
                    dotLayout.addView(dotView, params);
                    dotViewsList.add(dotView);
                }
                SendGiftAdapter2 adapter = new SendGiftAdapter2(mContext, bean.getResult().getGiftinfo(), imageOptions);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                recyclerview.setLayoutManager(layoutManager);
                recyclerview.setHasFixedSize(true);
                recyclerview.setAdapter(adapter);
                scrollHelper.setUpRecycleView(recyclerview);
                scrollHelper.setOnPageChangeListener(LiveActivity.this);
                llGift.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void onPageChange(int index) {
        for (int i = 0; i < dotViewsList.size(); i++) {
            if (i == index) {
                ((ImageView) dotViewsList.get(index)).setBackgroundResource(R.mipmap.dot_view_selected);
            } else {
                ((ImageView) dotViewsList.get(i)).setBackgroundResource(R.mipmap.dot_view_normal);
            }
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

    @Override
    public void changeCtrlView(boolean videoMember) {
    }
}
