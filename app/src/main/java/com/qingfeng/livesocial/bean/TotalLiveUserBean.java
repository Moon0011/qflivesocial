package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */

public class TotalLiveUserBean {


    /**
     * msg : y
     * result : [{"uid":4,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/9a8aa0552177cee4e464de23ebc0aa39.jpg","nickname":"测试","sex":"女","age":5,"signature":"测试","labels":"未设置","totaltime":"1234"},{"uid":21,"anchorpic":"http://video.520cai.cn/upload/ios/20170824/a5072309945e04f51f15ea5f4d543f94.png","nickname":"Good","sex":"男","age":23,"signature":"Hello world","labels":"可爱甜心,清新怡人,资深腐宅","totaltime":"1234"},{"uid":1,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170809/79b6c191b51d14fdf1d17f6ed35666b7.jpg","nickname":"测试","sex":"男","age":"未设置","signature":"111","labels":"未设置","totaltime":"0"},{"uid":2,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","sex":"女","age":"未设置","signature":"1111","labels":"未设置","totaltime":"0"},{"uid":3,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"测试1","sex":"女","age":"未设置","signature":"测试1","labels":"未设置","totaltime":"0"},{"uid":5,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/a7c7fa9fa53429bac09861f09c7064a5.jpg","nickname":"测试3","sex":"女","age":"未设置","signature":"测试","labels":"未设置","totaltime":"0"},{"uid":6,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/9a8aa0552177cee4e464de23ebc0aa39.jpg","nickname":"测试4","sex":"女","age":"未设置","signature":"测试","labels":"未设置","totaltime":"0"},{"uid":23,"anchorpic":"http://video.520cai.cn/upload/ios/20170822/c4718f6fbbc3e836ac5e18b5b70c8008.png","nickname":"Ggg","sex":"男","age":3,"signature":"Hellow","labels":"暖心治愈,清新怡人,可爱甜心","totaltime":"0"},{"uid":27,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170824/1cb7d22ee9704cccfc3b78f3c476c119.png","nickname":"干妹妹还是干妹妹","sex":"女","age":"未设置","signature":"中华文字博大精深~","labels":"未设置","totaltime":"0"},{"uid":28,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"cai","sex":"男","age":"未设置","signature":"這是一個測試","labels":"未设置","totaltime":"0"}]
     */

    private String msg;
    private List<LiveUserBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<LiveUserBean> getResult() {
        return result;
    }

    public void setResult(List<LiveUserBean> result) {
        this.result = result;
    }

    public static class LiveUserBean {
        /**
         * uid : 4
         * anchorpic : http://video.520cai.cn/upload/zhibo/20170817/9a8aa0552177cee4e464de23ebc0aa39.jpg
         * nickname : 测试
         * sex : 女
         * age : 5
         * signature : 测试
         * labels : 未设置
         * totaltime : 1234
         */

        private int uid;
        private String anchorpic;
        private String nickname;
        private String sex;
        private String age;
        private String signature;
        private String labels;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getTotaltime() {
            return totaltime;
        }

        public void setTotaltime(String totaltime) {
            this.totaltime = totaltime;
        }
    }
}
