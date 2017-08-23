package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class RecommedRespBean {

    /**
     * msg : y
     * result : [{"uid":5,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/a7c7fa9fa53429bac09861f09c7064a5.jpg","nickname":"测试3","age":"未设置","sex":"女","totaltime":"0"}]
     */

    private String msg;
    private List<RecommendBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RecommendBean> getResult() {
        return result;
    }

    public void setResult(List<RecommendBean> result) {
        this.result = result;
    }

    public static class RecommendBean {
        /**
         * uid : 5
         * anchorpic : http://video.520cai.cn/upload/zhibo/20170817/a7c7fa9fa53429bac09861f09c7064a5.jpg
         * nickname : 测试3
         * age : 未设置
         * sex : 女
         * totaltime : 0
         */

        private int uid;
        private String anchorpic;
        private String nickname;
        private String age;
        private String sex;
        private String totaltime;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAnchorpic() {
            return anchorpic;
        }

        public void setAnchorpic(String anchorpic) {
            this.anchorpic = anchorpic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTotaltime() {
            return totaltime;
        }

        public void setTotaltime(String totaltime) {
            this.totaltime = totaltime;
        }
    }
}
