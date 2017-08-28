package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */

public class AnchorDetailRespBean {

    /**
     * msg : y
     * result : {"video":"http://video.520cai.cn/upload/zhibo/20170824/1cb7d22ee9704cccfc3b78f3c476c119.png","voice":"未设置","nickname":"干妹妹还是干妹妹","sex":"女","age":"0","onlinestatus":0,"constellation":"未设置","address":"未设置","photo":[],"listen_setting":"未设置","video_setting":"未设置","voice_setting":"未设置","signature":"中华文字博大精深~","labels":"未设置","commentnum":"0","rating":"100%","attentionnum":null,"totaltime":0}
     */

    private String msg;
    private AnchorDetailBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AnchorDetailBean getResult() {
        return result;
    }

    public void setResult(AnchorDetailBean result) {
        this.result = result;
    }

    public static class AnchorDetailBean {
        /**
         * video : http://video.520cai.cn/upload/zhibo/20170824/1cb7d22ee9704cccfc3b78f3c476c119.png
         * voice : 未设置
         * nickname : 干妹妹还是干妹妹
         * sex : 女
         * age : 0
         * onlinestatus : 0
         * constellation : 未设置
         * address : 未设置
         * photo : []
         * listen_setting : 未设置
         * video_setting : 未设置
         * voice_setting : 未设置
         * signature : 中华文字博大精深~
         * labels : 未设置
         * commentnum : 0
         * rating : 100%
         * attentionnum : null
         * totaltime : 0
         */

        private String video;
        private String voice;
        private String nickname;
        private String sex;
        private String age;
        private int onlinestatus;
        private String constellation;
        private String address;
        private String listen_setting;
        private String video_setting;
        private String voice_setting;
        private String signature;
        private String labels;
        private String commentnum;
        private String rating;
        private Object attentionnum;
        private int totaltime;
        private List<?> photo;

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
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

        public int getOnlinestatus() {
            return onlinestatus;
        }

        public void setOnlinestatus(int onlinestatus) {
            this.onlinestatus = onlinestatus;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getListen_setting() {
            return listen_setting;
        }

        public void setListen_setting(String listen_setting) {
            this.listen_setting = listen_setting;
        }

        public String getVideo_setting() {
            return video_setting;
        }

        public void setVideo_setting(String video_setting) {
            this.video_setting = video_setting;
        }

        public String getVoice_setting() {
            return voice_setting;
        }

        public void setVoice_setting(String voice_setting) {
            this.voice_setting = voice_setting;
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

        public String getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(String commentnum) {
            this.commentnum = commentnum;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public Object getAttentionnum() {
            return attentionnum;
        }

        public void setAttentionnum(Object attentionnum) {
            this.attentionnum = attentionnum;
        }

        public int getTotaltime() {
            return totaltime;
        }

        public void setTotaltime(int totaltime) {
            this.totaltime = totaltime;
        }

        public List<?> getPhoto() {
            return photo;
        }

        public void setPhoto(List<?> photo) {
            this.photo = photo;
        }
    }
}
