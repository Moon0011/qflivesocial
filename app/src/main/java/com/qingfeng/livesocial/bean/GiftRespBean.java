package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class GiftRespBean {

    /**
     * msg : y
     * result : [{"giftname":"1111","gifticon":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","giftnum":1,"nickname":"11"},{"giftname":"1111","gifticon":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","giftnum":1,"nickname":"11"},{"giftname":"1111","gifticon":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","giftnum":2,"nickname":"11"}]
     */

    private String msg;
    private List<GiftBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<GiftBean> getResult() {
        return result;
    }

    public void setResult(List<GiftBean> result) {
        this.result = result;
    }

    public static class GiftBean {
        /**
         * giftname : 1111
         * gifticon : http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png
         * giftnum : 1
         * nickname : 11
         */

        private String giftname;
        private String gifticon;
        private int giftnum;
        private String nickname;

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public String getGifticon() {
            return gifticon;
        }

        public void setGifticon(String gifticon) {
            this.gifticon = gifticon;
        }

        public int getGiftnum() {
            return giftnum;
        }

        public void setGiftnum(int giftnum) {
            this.giftnum = giftnum;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
