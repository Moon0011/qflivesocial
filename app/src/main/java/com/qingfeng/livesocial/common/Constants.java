package com.qingfeng.livesocial.common;

/**
 * Created by Administrator on 2017/8/23.
 */

public class Constants {
    public static final String PARAM_A = "a";
    public static final String PARAM_Y = "y";


    public static final String PARAM_PHONE = "phone";
    public static final String PARAM_VERIFY_CODE = "code";
    public static final String VERIFY_CODE_TEST = "6666";

    public static final String PARAM_UID = "uid";
    public static final String PARAM_AUID = "attuid";
    public static final String PARAM_ATTENSTATUS = "attstatus";
    public static final String PARAM_PHOTO_ID = "id";
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_ANCHORPIC = "anchorpic";
    public static final String PARAM_NICKNAME = "nickname";
    public static final String PARAM_SIGNATURE = "signature";
    public static final String PARAM_LABEL = "label";
    public static final String PARAM_ADDRESS = "address";
    public static final String PARAM_BIRTHDAY = "birthday";
    public static final String PARAM_SEX = "sex";
    public static final String PARAM_HEADPIC = "headpic";
    public static final String PARAM_PHOTO = "photo";

    public static final String PARAM_ANCHORPIC_INFO_EDIT_TYPE_VALUE = "1";
    public static final String PARAM_NICKNAME_INFO_EDIT_TYPE_VALUE = "2";
    public static final String PARAM_SIGNATURE_INFO_EDIT_TYPE_VALUE = "3";
    public static final String PARAM_LABEL_INFO_EDIT_TYPE_VALUE = "4";
    public static final String PARAM_ADDRESS_INFO_EDIT_TYPE_VALUE = "5";
    public static final String PARAM_BIRTHDAY_INFO_EDIT_TYPE_VALUE = "6";
    public static final String PARAM_SEX_INFO_EDIT_TYPE_VALUE = "7";
    public static final String PARAM_HEADPIC_INFO_EDIT_TYPE_VALUE = "8";
    public static final String PARAM_PHOTO_INFO_EDIT_TYPE_VALUE = "9";
    public static final String PARAM_LABEL_FOR_PERSONAL_TYPE = "1";
    public static final String PARAM_LABEL_FOR_USER_TYPE = "2";

    public static final String PARAM_GIFT_RECEIVE_TYPE = "1";
    public static final String PARAM_GIFT_SEND_TYPE = "2";
    public static final String PARAM_ATTENTION_TYPE = "1";
    public static final String PARAM_FANS_TYPE = "2";

    public static final String ATTENTION_RANKLIST = "attention_ranklist";
    public static final String RICHER_RANKLIST = "richer_ranklist";
    public static final String GLAMOUR_RANKLIST = "glamour_ranklist";
    public static final String DAY_RANKLIST_NAME = "日榜";
    public static final String WEEK_RANKLIST_NAME = "周榜";
    public static final String TOTAL_RANKLIST_NAME = "总榜";
    public static final String ATTENTION_RANKLIST_NAME = "关注榜";
    public static final String RICHER_RANKLIST_NAME = "土豪榜";
    public static final String GLAMOUR_RANKLIST_NAME = "魅力榜";
    public static final String PARAM_PARENT_RANKLIST_TYPE = "type";//父榜：日 周 总榜
    public static final String PARAM_PARENT_RANKLIST_TYPE_DAY_VALUE = "1";
    public static final String PARAM_PARENT_RANKLIST_TYPE_WEEK_VALUE = "2";
    public static final String PARAM_PARENT_RANKLIST_TYPE_TOTAL_VALUE = "3";
    public static final String PARAM_CHILDREN_RANKLIST_TYPE = "stype";//子榜：光注 土豪 魅力榜
    public static final String PARAM_CHILDREN_RANKLIST_TYPE_ATTENTION_VALUE = "1";
    public static final String PARAM_CHILDREN_RANKLIST_TYPE_RICHER_VALUE = "2";
    public static final String PARAM_CHILDREN_RANKLIST_TYPE_GLAMOUR_VALUE = "3";

    public static final String HOME_SLIDE_IMG = "home_slide_img";
    public static final String HOME_TOTAL_ANCHOR = "home_total_anchor";
    public static final String HOME_POPULAR_ANCHOR = "home_popular_anchor";
    public static final String HOME_YOUNGSHOW_ANCHOR = "home_youngshow_anchor";
    public static final String HOME_RECOMMEND_ANCHOR = "home_recommend_anchor";

    public static final String MY_GIFT = "我的礼物";
    public static final String SEND_GIFT = "送的礼物";
    public static final String ATTENTION = "关注";
    public static final String ALL = "全部";
    public static final String FANS = "粉丝";

    public static final String BD_EXIT_APP = "bd_sxb_exit";
    public static final String USER_INFO = "user_info";
    public static final String USER_ID = "user_id";
    public static final String USER_SIG = "user_sig";
    public static final String USER_TOKEN = "user_token";
    public static final String USER_NICK = "user_nick";
    public static final String USER_SIGN = "user_sign";
    public static final String USER_AVATAR = "user_avatar";
    public static final String USER_ROOM_NUM = "user_room_num";
    public static final String LIVE_ANIMATOR = "live_animator";
    public static final String LOG_LEVEL = "log_level";
    public static final String BEAUTY_TYPE = "beauty_type";
    public static final String VIDEO_QULITY = "video_qulity";
    public static final String HD_ROLE = "HD";
    public static final int SDK_APPID = 1400037547;
    public static final int ACCOUNT_TYPE = 14604;
    public static final int HOST = 1;
    public static final int MEMBER = 0;
    public static final int VIDEO_MEMBER = 2;
    public static final int AVIMCMD_TEXT = -1;         // 普通的聊天消息
    public static final int AVIMCMD_NONE = AVIMCMD_TEXT + 1;               // 无事件
    // 以下事件为TCAdapter内部处理的通用事件
    public static final int AVIMCMD_ENTERLIVE = AVIMCMD_NONE + 1;          // 用户加入直播,
    public static final int AVIMCMD_EXITLIVE = AVIMCMD_ENTERLIVE + 1;         // 用户退出直播,
    public static final int AVIMCMD_PRAISE = AVIMCMD_EXITLIVE + 1;           // 点赞消息,
    public static final int AVIMCMD_HOST_LEAVE = AVIMCMD_PRAISE + 1;         // 主播离开,
    public static final int AVIMCMD_HOST_BACK = AVIMCMD_HOST_LEAVE + 1;      // 主播回来,
    public static final int AVIMCMD_MULTI = 0x800;             // 多人互动消息类型
    public static final int AVIMCMD_MUlTI_HOST_INVITE = AVIMCMD_MULTI + 1;         // 邀请互动,
    public static final int AVIMCMD_MULTI_CANCEL_INTERACT = AVIMCMD_MUlTI_HOST_INVITE + 1;       // 断开互动，
    public static final int AVIMCMD_MUlTI_JOIN = AVIMCMD_MULTI_CANCEL_INTERACT + 1;       // 同意互动，
    public static final int AVIMCMD_MUlTI_REFUSE = AVIMCMD_MUlTI_JOIN + 1;      // 拒绝互动，
    public static final int AVIMCMD_MULTI_HOST_ENABLEINTERACTMIC = AVIMCMD_MUlTI_REFUSE + 1;  // 主播打开互动者Mic，
    public static final int AVIMCMD_MULTI_HOST_DISABLEINTERACTMIC = AVIMCMD_MULTI_HOST_ENABLEINTERACTMIC + 1;// 主播关闭互动者Mic，
    public static final int AVIMCMD_MULTI_HOST_ENABLEINTERACTCAMERA = AVIMCMD_MULTI_HOST_DISABLEINTERACTMIC + 1; // 主播打开互动者Camera，
    public static final int AVIMCMD_MULTI_HOST_DISABLEINTERACTCAMERA = AVIMCMD_MULTI_HOST_ENABLEINTERACTCAMERA + 1; // 主播打开互动者Camera
    public static final int AVIMCMD_MULTI_HOST_CANCELINVITE = AVIMCMD_MULTI_HOST_DISABLEINTERACTCAMERA + 1; //主播让某个互动者下麦
    public static final int AVIMCMD_MULTI_HOST_CONTROLL_CAMERA = AVIMCMD_MULTI_HOST_CANCELINVITE + 1; //主播控制某个上麦成员摄像头
    public static final int AVIMCMD_MULTI_HOST_CONTROLL_MIC = AVIMCMD_MULTI_HOST_CONTROLL_CAMERA + 1; //主播控制某个上麦成员MIC
    public static final int AVIMCMD_MULTI_HOST_SWITCH_CAMERA = AVIMCMD_MULTI_HOST_CONTROLL_MIC + 1; ////主播切换某个上麦成员MIC
    public static final String VIDEO_MEMBER_ROLE = "LiveGuest";
    public static final String NORMAL_MEMBER_ROLE = "Guest";

}
