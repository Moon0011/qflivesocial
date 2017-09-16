package com.qingfeng.livesocial.live.viewinface;


import com.qingfeng.livesocial.bean.LiveInfoJson;
import com.qingfeng.livesocial.bean.MemberID;

import java.util.ArrayList;

/**
 * 直播界面回调
 */
public interface LiveView extends MvpView {

    void enterRoomComplete(int id_status, boolean succ);

    void quiteRoomComplete(int id_status, boolean succ, LiveInfoJson liveinfo);

    void showInviteDialog();

    void refreshText(String text, String name);

    void refreshThumbUp();

    void changeCtrlView(boolean bVideoMember);

    boolean showInviteView(String id);

    void cancelInviteView(String id);

    void succInviteView(String id);

    void agreeAnswer();

    void cancelMemberView(String id);

    void memberJoin(String id, String name);

    void hideInviteDialog();

    void hostLeave(String id, String name);

    void hostBack(String id, String name);

    void refreshMember(ArrayList<MemberID> memlist);

    void linkRoomReq(String id, String name);

    void linkRoomAccept(String id, String strRoomId);

    void forceQuitRoom(String strMessage);

    void refuseLink(String msg);//拒绝连麦邀请

    void unlinkRoom();

    void callingHangup(String id);
}
