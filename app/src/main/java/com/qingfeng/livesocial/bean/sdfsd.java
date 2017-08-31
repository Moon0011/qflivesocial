package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class sdfsd {

    /**
     * msg : y
     * result : {"balance":0,"giftinfo":[{"giftname":"滑稽","gifticon":"/upload/zhibo/20170824/629d816a957875faaaca341855acbe9e.png","needcoin":0},{"giftname":"爱心","gifticon":"/upload/zhibo/20170824/dd14535ef7769da8c506b7c9648c25cb.png","needcoin":0},{"giftname":"爱心甜甜圈","gifticon":"/upload/zhibo/20170824/5dca98f0b8c4cb87c1f1748ff796ab5a.png","needcoin":0},{"giftname":"棒棒哒","gifticon":"/upload/zhibo/20170824/f21a3d7b03c21eebf25044915cbf1e1b.png","needcoin":0},{"giftname":"棒棒糖","gifticon":"/upload/zhibo/20170824/1fd9630b63d3df8aafae0298f3a00307.png","needcoin":0},{"giftname":"肥皂","gifticon":"/upload/zhibo/20170824/e2e75fd914ff47357fde86e5f79b29ee.png","needcoin":0},{"giftname":"喵星人","gifticon":"/upload/zhibo/20170824/cb3f21630d0fca249691d4b4adf4afcf.png","needcoin":0},{"giftname":"情书","gifticon":"/upload/zhibo/20170824/da40229d728b6d42b454313dd7ff0b8a.png","needcoin":0},{"giftname":"去污粉","gifticon":"/upload/zhibo/20170824/618edca4758199ea06a714c3e65e558c.png","needcoin":0},{"giftname":"学生卡","gifticon":"/upload/zhibo/20170824/b07970dc60db140e23082e57b7c15779.png","needcoin":0},{"giftname":"药不能停","gifticon":"/upload/zhibo/20170824/0184660d5c14181d6a871f272c517c5f.png","needcoin":0},{"giftname":"应援荧光棒","gifticon":"/upload/zhibo/20170824/f8d00f6c958f1c70ade864e92c94d4bc.png","needcoin":0},{"giftname":"元气弹","gifticon":"/upload/zhibo/20170824/239015d61bd91c0175cb17b681a6d3d7.png","needcoin":0}]}
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
         * balance : 0
         * giftinfo : [{"giftname":"滑稽","gifticon":"/upload/zhibo/20170824/629d816a957875faaaca341855acbe9e.png","needcoin":0},{"giftname":"爱心","gifticon":"/upload/zhibo/20170824/dd14535ef7769da8c506b7c9648c25cb.png","needcoin":0},{"giftname":"爱心甜甜圈","gifticon":"/upload/zhibo/20170824/5dca98f0b8c4cb87c1f1748ff796ab5a.png","needcoin":0},{"giftname":"棒棒哒","gifticon":"/upload/zhibo/20170824/f21a3d7b03c21eebf25044915cbf1e1b.png","needcoin":0},{"giftname":"棒棒糖","gifticon":"/upload/zhibo/20170824/1fd9630b63d3df8aafae0298f3a00307.png","needcoin":0},{"giftname":"肥皂","gifticon":"/upload/zhibo/20170824/e2e75fd914ff47357fde86e5f79b29ee.png","needcoin":0},{"giftname":"喵星人","gifticon":"/upload/zhibo/20170824/cb3f21630d0fca249691d4b4adf4afcf.png","needcoin":0},{"giftname":"情书","gifticon":"/upload/zhibo/20170824/da40229d728b6d42b454313dd7ff0b8a.png","needcoin":0},{"giftname":"去污粉","gifticon":"/upload/zhibo/20170824/618edca4758199ea06a714c3e65e558c.png","needcoin":0},{"giftname":"学生卡","gifticon":"/upload/zhibo/20170824/b07970dc60db140e23082e57b7c15779.png","needcoin":0},{"giftname":"药不能停","gifticon":"/upload/zhibo/20170824/0184660d5c14181d6a871f272c517c5f.png","needcoin":0},{"giftname":"应援荧光棒","gifticon":"/upload/zhibo/20170824/f8d00f6c958f1c70ade864e92c94d4bc.png","needcoin":0},{"giftname":"元气弹","gifticon":"/upload/zhibo/20170824/239015d61bd91c0175cb17b681a6d3d7.png","needcoin":0}]
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
             * giftname : 滑稽
             * gifticon : /upload/zhibo/20170824/629d816a957875faaaca341855acbe9e.png
             * needcoin : 0
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
