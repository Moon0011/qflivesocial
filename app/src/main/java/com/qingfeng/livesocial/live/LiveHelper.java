package com.qingfeng.livesocial.live;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.MySelfInfo;
import com.qingfeng.livesocial.common.Constants;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.live.viewinface.LiveView;
import com.qingfeng.livesocial.util.SxbLog;
import com.tencent.TIMConversationType;
import com.tencent.TIMCustomElem;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMGroupSystemElem;
import com.tencent.TIMGroupSystemElemType;
import com.tencent.TIMMessage;
import com.tencent.av.sdk.AVRoomMulti;
import com.tencent.av.sdk.AVVideoCtrl;
import com.tencent.av.sdk.AVView;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.core.ILiveRoomOption;
import com.tencent.livesdk.ILVChangeRoleRes;
import com.tencent.livesdk.ILVCustomCmd;
import com.tencent.livesdk.ILVLiveConstants;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.livesdk.ILVLiveRoomOption;
import com.tencent.livesdk.ILVText;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Observable;
import java.util.Observer;


/**
 * 直播控制类
 */
public class LiveHelper extends Presenter implements ILiveRoomOption.onRoomDisconnectListener, Observer {
    private final String TAG = "LiveHelper";
    private LiveView mLiveView;
    public Context mContext;
    private boolean bCameraOn = false;
    private boolean bMicOn = false;
    private boolean flashLgihtStatus = false;

    /**
     * 申请房间
     */
    private void startCreateRoom() {
        createRoom();
    }

    public LiveHelper(Context context, LiveView liveview) {
        mContext = context;
        mLiveView = liveview;
        MessageEvent.getInstance().addObserver(this);
    }

    @Override
    public void onDestory() {
        mLiveView = null;
        mContext = null;
        MessageEvent.getInstance().deleteObserver(this);
        ILVLiveManager.getInstance().quitRoom(null);
    }

    /**
     * 进入房间
     */
    public void startEnterRoom() {
        if (MySelfInfo.getInstance().isCreateRoom() == true) {
            startCreateRoom();
        } else {
            joinRoom();
        }
    }

    public void switchRoom() {
        ILVLiveRoomOption memberOption = new ILVLiveRoomOption(Constants.hostId)
                .autoCamera(false)
                .autoFocus(true)
                .roomDisconnectListener(this)
                .videoMode(ILiveConstants.VIDEOMODE_BSUPPORT)
                .controlRole(Constants.NORMAL_MEMBER_ROLE)
                .authBits(AVRoomMulti.AUTH_BITS_JOIN_ROOM | AVRoomMulti.AUTH_BITS_RECV_AUDIO | AVRoomMulti.AUTH_BITS_RECV_CAMERA_VIDEO | AVRoomMulti.AUTH_BITS_RECV_SCREEN_VIDEO)
                .videoRecvMode(AVRoomMulti.VIDEO_RECV_MODE_SEMI_AUTO_RECV_CAMERA_VIDEO)
                .autoMic(false);
        ILVLiveManager.getInstance().switchRoom(Constants.roomNum, memberOption, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                ILiveLog.d(TAG, "ILVB-Suixinbo|switchRoom->join room sucess");
                if (null != mLiveView) {
                    mLiveView.enterRoomComplete(MySelfInfo.getInstance().getIdStatus(), true);
                }
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ILiveLog.d(TAG, "ILVB-Suixinbo|switchRoom->join room failed:" + module + "|" + errCode + "|" + errMsg);
                if (null != mLiveView) {
                    mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                }
            }
        });
    }

    private void showToast(String strMsg) {
        if (null != mContext) {
            Toast.makeText(mContext, strMsg, Toast.LENGTH_SHORT).show();
        }
    }

    private void showUserToast(String account, int resId) {
        if (null != mContext) {
            Toast.makeText(mContext, account + mContext.getString(resId), Toast.LENGTH_SHORT).show();
        }
    }

    private void quitLiveRoom() {
        ILVLiveManager.getInstance().quitRoom(new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                ILiveLog.d(TAG, "ILVB-SXB|quitRoom->success");
                if (null != mLiveView) {
                    mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                }
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ILiveLog.d(TAG, "ILVB-SXB|quitRoom->failed:" + module + "|" + errCode + "|" + errMsg);
                if (null != mLiveView) {
                    mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                }
            }
        });
    }

    public void startExitRoom() {
        ILiveSDK.getInstance().getAvVideoCtrl().setLocalVideoPreProcessCallback(null);
        quitLiveRoom();
    }

    /**
     * 发送信令
     */

    public int sendGroupCmd(int cmd, String param) {
        ILVCustomCmd customCmd = new ILVCustomCmd();
        customCmd.setCmd(cmd);
        customCmd.setParam(param);
        customCmd.setType(ILVText.ILVTextType.eGroupMsg);
        return sendCmd(customCmd);
    }

    public int sendC2CCmd(final int cmd, String param, String destId) {
        ILVCustomCmd customCmd = new ILVCustomCmd();
        customCmd.setDestId(destId);
        customCmd.setCmd(cmd);
        customCmd.setParam(param);
        customCmd.setType(ILVText.ILVTextType.eC2CMsg);
        return sendCmd(customCmd);
    }

    /**
     * 打开闪光灯
     */
    public boolean toggleFlashLight() {
        AVVideoCtrl videoCtrl = ILiveSDK.getInstance().getAvVideoCtrl();
        if (null == videoCtrl) {
            return false;
        }

        final Object cam = videoCtrl.getCamera();
        if ((cam == null) || (!(cam instanceof Camera))) {
            return false;
        }
        final Camera.Parameters camParam = ((Camera) cam).getParameters();
        if (null == camParam) {
            return false;
        }

        Object camHandler = videoCtrl.getCameraHandler();
        if ((camHandler == null) || (!(camHandler instanceof Handler))) {
            return false;
        }

        //对摄像头的操作放在摄像头线程
        if (flashLgihtStatus == false) {
            ((Handler) camHandler).post(new Runnable() {
                public void run() {
                    try {
                        camParam.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        ((Camera) cam).setParameters(camParam);
                        flashLgihtStatus = true;
                    } catch (RuntimeException e) {
                        SxbLog.d("setParameters", "RuntimeException");
                    }
                }
            });
        } else {
            ((Handler) camHandler).post(new Runnable() {
                public void run() {
                    try {
                        camParam.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        ((Camera) cam).setParameters(camParam);
                        flashLgihtStatus = false;
                    } catch (RuntimeException e) {
                        SxbLog.d("setParameters", "RuntimeException");
                    }

                }
            });
        }
        return true;
    }


    @Override
    public void onRoomDisconnect(int errCode, String errMsg) {
        if (null != mLiveView) {
            mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
        }
    }

    // 解析文本消息
    private void processTextMsg(MessageEvent.SxbMsgInfo info) {
        if (null == info.data || !(info.data instanceof ILVText)) {
            SxbLog.w(TAG, "processTextMsg->wrong object:" + info.data);
            return;
        }
        ILVText text = (ILVText) info.data;
        if (text.getType() == ILVText.ILVTextType.eGroupMsg
                && !Constants.getChatRoomId().equals(text.getDestId())) {
            return;
        }

        String name = info.senderId;
        if (null != info.profile && !TextUtils.isEmpty(info.profile.getNickName())) {
            name = info.profile.getNickName();
        }

        if (null != mLiveView)
            mLiveView.refreshText(text.getText(), name);
    }

    // 解析自定义信令
    private void processCmdMsg(MessageEvent.SxbMsgInfo info) {
        if (null == info.data || !(info.data instanceof ILVCustomCmd)) {
            SxbLog.w(TAG, "processCmdMsg->wrong object:" + info.data);
            return;
        }
        ILVCustomCmd cmd = (ILVCustomCmd) info.data;
        if (cmd.getType() == ILVText.ILVTextType.eGroupMsg
                && !Constants.getChatRoomId().equals(cmd.getDestId())) {
            SxbLog.d(TAG, "processCmdMsg->ingore message from: " + cmd.getDestId() + "/" + Constants.getChatRoomId());
            return;
        }

        String name = info.senderId;
        if (null != info.profile && !TextUtils.isEmpty(info.profile.getNickName())) {
            name = info.profile.getNickName();
        }

        handleCustomMsg(cmd.getCmd(), cmd.getParam(), info.senderId, name);
    }

    private void processOtherMsg(MessageEvent.SxbMsgInfo info) {
        if (null == info.data || !(info.data instanceof TIMMessage)) {
            SxbLog.w(TAG, "processOtherMsg->wrong object:" + info.data);
            return;
        }
        TIMMessage currMsg = (TIMMessage) info.data;

        // 过滤非当前群组消息
        if (currMsg.getConversation() != null && currMsg.getConversation().getPeer() != null) {
            if (currMsg.getConversation().getType() == TIMConversationType.Group
                    && !Constants.getChatRoomId().equals(currMsg.getConversation().getPeer())) {
                return;
            }
        }

        for (int j = 0; j < currMsg.getElementCount(); j++) {
            if (currMsg.getElement(j) == null)
                continue;
            TIMElem elem = currMsg.getElement(j);
            TIMElemType type = elem.getType();

            SxbLog.d(TAG, "LiveHelper->otherMsg type:" + type);

            //系统消息
            if (type == TIMElemType.GroupSystem) {  // 群组解散消息
                if (TIMGroupSystemElemType.TIM_GROUP_SYSTEM_DELETE_GROUP_TYPE == ((TIMGroupSystemElem) elem).getSubtype()) {
                    if (null != mLiveView)
                        mLiveView.hostLeave("host", null);
                }
            } else if (type == TIMElemType.Custom) {
                try {
                    final String strMagic = "__ACTION__";
                    String customText = new String(((TIMCustomElem) elem).getData(), "UTF-8");
                    if (!customText.startsWith(strMagic))   // 检测前缀
                        continue;
                    JSONTokener jsonParser = new JSONTokener(customText.substring(strMagic.length() + 1));
                    JSONObject json = (JSONObject) jsonParser.nextValue();
                    String action = json.optString("action", "");
                    if (action.equals("force_exit_room") || action.equals("force_disband_room")) {
                        JSONObject objData = json.getJSONObject("data");
                        String strRoomNum = objData.optString("room_num", "");
                        SxbLog.d(TAG, "processOtherMsg->action:" + action + ", room_num:" + strRoomNum);
                        if (strRoomNum.equals(String.valueOf(ILiveRoomManager.getInstance().getRoomId()))) {
                            if (null != mLiveView) {
                                mLiveView.forceQuitRoom(mContext.getString(R.string.str_tips_force_exit));
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        MessageEvent.SxbMsgInfo info = (MessageEvent.SxbMsgInfo) o;
        switch (info.msgType) {
            case MessageEvent.MSGTYPE_TEXT:
                processTextMsg(info);
                break;
            case MessageEvent.MSGTYPE_CMD:
                processCmdMsg(info);
                break;
            case MessageEvent.MSGTYPE_OTHER:
                processOtherMsg(info);
                break;
        }
    }

    public void toggleCamera() {
        bCameraOn = !bCameraOn;
        SxbLog.d(TAG, "toggleCamera->change camera:" + bCameraOn);
        ILiveRoomManager.getInstance().enableCamera(ILiveRoomManager.getInstance().getCurCameraId(), bCameraOn);
    }

    public void upMemberVideo() {
        if (!ILiveRoomManager.getInstance().isEnterRoom()) {
            SxbLog.e(TAG, "upMemberVideo->with not in room");
        }
        ILVLiveManager.getInstance().upToVideoMember(Constants.VIDEO_MEMBER_ROLE, true, true, new ILiveCallBack<ILVChangeRoleRes>() {
            @Override
            public void onSuccess(ILVChangeRoleRes data) {
                SxbLog.d(TAG, "upToVideoMember->success");
                MySelfInfo.getInstance().setIdStatus(Constants.VIDEO_MEMBER);
                bMicOn = true;
                bCameraOn = true;
                mLiveView.agreeAnswer();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                SxbLog.e(TAG, "upToVideoMember->failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }

    public void downMemberVideo() {
        if (!ILiveRoomManager.getInstance().isEnterRoom()) {
            SxbLog.e(TAG, "downMemberVideo->with not in room");
        }
        ILVLiveManager.getInstance().downToNorMember(Constants.NORMAL_MEMBER_ROLE, new ILiveCallBack<ILVChangeRoleRes>() {
            @Override
            public void onSuccess(ILVChangeRoleRes data) {
                MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
                bMicOn = false;
                bCameraOn = false;
                SxbLog.e(TAG, "downMemberVideo->onSuccess");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                SxbLog.e(TAG, "downMemberVideo->failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }

    private void checkEnterReturn(int iRet) {
        if (ILiveConstants.NO_ERR != iRet) {
            ILiveLog.d(TAG, "ILVB-Suixinbo|checkEnterReturn->enter room failed:" + iRet);
            if (ILiveConstants.ERR_ALREADY_IN_ROOM == iRet) {     // 上次房间未退出处理做退出处理
                ILiveRoomManager.getInstance().quitRoom(new ILiveCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        if (null != mLiveView) {
                            mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                        }
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {
                        if (null != mLiveView) {
                            mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                        }
                    }
                });
            } else {
                if (null != mLiveView) {
                    mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                }
            }
        }
    }

    private void createRoom() {
        ILVLiveRoomOption hostOption = new ILVLiveRoomOption(QFApplication.getInstance().getLoginUser().getUsername())
                .roomDisconnectListener(this)
                .videoMode(ILiveConstants.VIDEOMODE_BSUPPORT)
                .controlRole(Constants.curRole)
                .autoFocus(true)
                .authBits(AVRoomMulti.AUTH_BITS_DEFAULT)
                .videoRecvMode(AVRoomMulti.VIDEO_RECV_MODE_SEMI_AUTO_RECV_CAMERA_VIDEO);
        int ret = ILVLiveManager.getInstance().createRoom(QFApplication.getInstance().getLoginUser().getCurroomnum(), hostOption, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                ILiveLog.d(TAG, "ILVB-SXB|startEnterRoom->create room sucess");
                bCameraOn = true;
                bMicOn = true;
                if (null != mLiveView)
                    mLiveView.enterRoomComplete(MySelfInfo.getInstance().getIdStatus(), true);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ILiveLog.d(TAG, "ILVB-SXB|createRoom->create room failed:" + module + "|" + errCode + "|" + errMsg);
                showToast("sendCmd->failed:" + module + "|" + errCode + "|" + errMsg);
                if (null != mLiveView) {
                    mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                }
            }
        });
        checkEnterReturn(ret);
    }

    private void joinRoom() {
        ILVLiveRoomOption memberOption = new ILVLiveRoomOption(Constants.hostId)
                .autoCamera(false)
                .roomDisconnectListener(this)
                .videoMode(ILiveConstants.VIDEOMODE_BSUPPORT)
                .controlRole(MySelfInfo.getInstance().getGuestRole())
                .authBits(AVRoomMulti.AUTH_BITS_JOIN_ROOM | AVRoomMulti.AUTH_BITS_RECV_AUDIO | AVRoomMulti.AUTH_BITS_RECV_CAMERA_VIDEO | AVRoomMulti.AUTH_BITS_RECV_SCREEN_VIDEO)
                .videoRecvMode(AVRoomMulti.VIDEO_RECV_MODE_SEMI_AUTO_RECV_CAMERA_VIDEO)
                .autoMic(false);
        int ret = ILVLiveManager.getInstance().joinRoom(Constants.roomNum, memberOption, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                ILiveLog.d(TAG, "ILVB-Suixinbo|startEnterRoom->join room sucess");
                if (null != mLiveView)
                    mLiveView.enterRoomComplete(MySelfInfo.getInstance().getIdStatus(), true);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ILiveLog.d(TAG, "ILVB-Suixinbo|startEnterRoom->join room failed:" + module + "|" + errCode + "|" + errMsg);
                ILiveLog.d(TAG, "ILVB-SXB|createRoom->create room failed:" + module + "|" + errCode + "|" + errMsg);
                if (null != mLiveView) {
                    mLiveView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
                }
            }
        });
        checkEnterReturn(ret);
        SxbLog.i(TAG, "joinLiveRoom startEnterRoom ");
    }

    private int sendCmd(final ILVCustomCmd cmd) {
        return ILVLiveManager.getInstance().sendCustomCmd(cmd, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                SxbLog.i(TAG, "sendCmd->success:" + cmd.getCmd() + "|" + cmd.getParam());
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
//                Toast.makeText(mContext, "sendCmd->failed:" + module + "|" + errCode + "|" + errMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleCustomMsg(int action, String param, String identifier, String nickname) {
        SxbLog.d(TAG, "handleCustomMsg->action: " + action);
        if (null == mLiveView) {
            return;
        }
        switch (action) {
            case Constants.AVIMCMD_MUlTI_HOST_INVITE:
                mLiveView.showInviteDialog();
                break;
            case Constants.AVIMCMD_MUlTI_JOIN:
                SxbLog.i(TAG, "handleCustomMsg " + identifier);
                mLiveView.succInviteView(identifier);
                showToast(identifier + " agree !");
                break;
            case Constants.AVIMCMD_MUlTI_REFUSE:
                mLiveView.cancelInviteView(identifier);
                showToast(identifier + " refuse !");
                break;
            case Constants.AVIMCMD_PRAISE:
                mLiveView.refreshThumbUp();
                break;
            case Constants.AVIMCMD_ENTERLIVE:
                mLiveView.memberJoin(identifier, nickname);
                break;
            case Constants.AVIMCMD_MULTI_CANCEL_INTERACT://主播关闭摄像头命令
                //如果是自己关闭Camera和Mic
                if (param.equals(MySelfInfo.getInstance().getId())) {//是自己
                    //被动下麦 下麦 下麦
                    downMemberVideo();
                }
                //其他人关闭小窗口
                ILiveRoomManager.getInstance().getRoomView().closeUserView(param, AVView.VIDEO_SRC_TYPE_CAMERA, true);
                mLiveView.callingHangup(param);
                break;
            case Constants.AVIMCMD_MULTI_HOST_CANCELINVITE:
                mLiveView.hideInviteDialog();
                break;
            case Constants.AVIMCMD_EXITLIVE:
                //startExitRoom();
                mLiveView.forceQuitRoom(mContext.getString(R.string.str_room_discuss));
                break;
            case ILVLiveConstants.ILVLIVE_CMD_LINKROOM_REQ:     // 跨房邀请
                mLiveView.linkRoomReq(identifier, nickname);
                break;
            case ILVLiveConstants.ILVLIVE_CMD_LINKROOM_ACCEPT:  // 接听
                mLiveView.linkRoomAccept(identifier, param);
                break;
            case ILVLiveConstants.ILVLIVE_CMD_LINKROOM_REFUSE:  // 拒绝
                mLiveView.refuseLink(identifier + mContext.getString(R.string.str_link_refuse));
                break;
            case ILVLiveConstants.ILVLIVE_CMD_LINKROOM_LIMIT:   // 达到上限
                showUserToast(identifier, R.string.str_link_limit);
                break;
            case ILVLiveConstants.ILVLIVE_CMD_UNLINKROOM:
                mLiveView.unlinkRoom();
                break;
            case Constants.AVIMCMD_HOST_BACK:
                mLiveView.hostBack(identifier, nickname);
            default:
                break;
        }
    }

    public void changeRole(final String role) {
        ILiveRoomManager.getInstance().changeRole(role, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                showToast("change " + role + " succ !!");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                showToast("change " + role + "   failed  : " + errCode + " msg " + errMsg);
            }
        });
    }

    public void sendLinkReq(final String dstId) {
        ILVLiveManager.getInstance().linkRoomRequest(dstId, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                showToast("sendLinkReq " + dstId + " succ !!");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                showToast("sendLinkReq " + dstId + " failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }

    public void unlinkRoom() {
        ILVLiveManager.getInstance().unlinkRoom(new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                showToast("unlinkRoom succ !!");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                showToast("unlinkRoom failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }

    public void acceptLink(String id) {
        ILVLiveManager.getInstance().acceptLinkRoom(id, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                showToast("acceptLinkRoom succ !!");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                showToast("unlinkRoom failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }

    public void refuseLink(String id) {
        ILVLiveManager.getInstance().refuseLinkRoom(id, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                showToast("refuseLinkRoom succ !!");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                showToast("refuseLinkRoom failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }

    public void linkRoom(String id, String room, String sign) {
        ILVLiveManager.getInstance().linkRoom(Integer.valueOf(room), id, sign, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                showToast("linkRoom success!!");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                showToast("linkRoom failed:" + module + "|" + errCode + "|" + errMsg);
            }
        });
    }
}
