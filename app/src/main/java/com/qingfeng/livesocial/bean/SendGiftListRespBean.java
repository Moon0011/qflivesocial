package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class SendGiftListRespBean  {

    /**
     * msg : y
     * result : {"balance":10,"giftinfo":[{"giftname":"1111","gifticon":"/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","needcoin":111},{"giftname":"11","gifticon":"/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","needcoin":111}]}
     */

    private String msg;
    private ResultBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * balance : 10
         * giftinfo : [{"giftname":"1111","gifticon":"/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","needcoin":111},{"giftname":"11","gifticon":"/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","needcoin":111}]
         */

        private int balance;
        private List<GiftinfoBean> giftinfo;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<GiftinfoBean> getGiftinfo() {
            return giftinfo;
        }

        public void setGiftinfo(List<GiftinfoBean> giftinfo) {
            this.giftinfo = giftinfo;
        }

        public static class GiftinfoBean {
            /**
             * giftname : 1111
             * gifticon : /upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png
             * needcoin : 111
             */

            private String giftname;
            private String gifticon;
            private int needcoin;

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

            public int getNeedcoin() {
                return needcoin;
            }

            public void setNeedcoin(int needcoin) {
                this.needcoin = needcoin;
            }
        }
    }
}
