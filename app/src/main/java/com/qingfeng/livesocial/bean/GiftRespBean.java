package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class GiftRespBean {

    /**
     * msg : y
     * result : [{"date":"2017-08-30","info":[{"giftname":"爱心甜甜圈","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/5dca98f0b8c4cb87c1f1748ff796ab5a.png","giftnum":2,"addtime":1504093327,"nickname":"测试4"},{"giftname":"爱心","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/dd14535ef7769da8c506b7c9648c25cb.png","giftnum":1,"addtime":1504093327,"nickname":"测试3"}]},{"date":"2017-08-12","info":[{"giftname":"滑稽","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/629d816a957875faaaca341855acbe9e.png","giftnum":1,"addtime":1502506943,"nickname":"测试"}]},{"date":"2017-08-11","info":[{"giftname":"肥皂","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/e2e75fd914ff47357fde86e5f79b29ee.png","giftnum":1,"addtime":1502420543,"nickname":null}]},{"date":"2017-08-02","info":[{"giftname":"滑稽","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/629d816a957875faaaca341855acbe9e.png","giftnum":1,"addtime":1501688000,"nickname":"测试"}]},{"date":"2016-08-01","info":[{"giftname":"棒棒哒","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/f21a3d7b03c21eebf25044915cbf1e1b.png","giftnum":1,"addtime":1470020543,"nickname":null}]}]
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
         * date : 2017-08-30
         * info : [{"giftname":"爱心甜甜圈","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/5dca98f0b8c4cb87c1f1748ff796ab5a.png","giftnum":2,"addtime":1504093327,"nickname":"测试4"},{"giftname":"爱心","gifticon":"http://video.520cai.cn/upload/zhibo/20170824/dd14535ef7769da8c506b7c9648c25cb.png","giftnum":1,"addtime":1504093327,"nickname":"测试3"}]
         */

        private String date;
        private List<InfoBean> info;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * giftname : 爱心甜甜圈
             * gifticon : http://video.520cai.cn/upload/zhibo/20170824/5dca98f0b8c4cb87c1f1748ff796ab5a.png
             * giftnum : 2
             * addtime : 1504093327
             * nickname : 测试4
             */

            private String giftname;
            private String gifticon;
            private int giftnum;
            private int addtime;
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

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
