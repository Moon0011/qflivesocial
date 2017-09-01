package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */

public class AnchorDetailRespBean {


    /**
     * msg : y
     * result : {"video":"http://video.520cai.cn/upload/ios/20170824/a5072309945e04f51f15ea5f4d543f94.png","voice":"未设置","nickname":"老蔡","sex":"男","age":23,"onlinestatus":1,"constellation":"处女座","address":"黑龙江哈尔滨市市辖区","photo":["http://video.520cai.cn/upload/ios/20170824/a612c36ef52104a18218b5eb4eb29041.png","http://video.520cai.cn/upload/ios/20170824/6d37a820737e895460a2fb300535187d.png","http://video.520cai.cn/upload/ios/20170824/385438af421204448fd311ea8294b6b7.png"],"listen_setting":"未设置","video_setting":"未设置","voice_setting":"未设置","signature":"Hello world","labels":"可爱甜心,清新怡人,资深腐宅","commentnum":"0","rating":"100%","attentionnum":1,"totaltime":21}
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
         * video : http://video.520cai.cn/upload/ios/20170824/a5072309945e04f51f15ea5f4d543f94.png
         * voice : 未设置
         * nickname : 老蔡
         * sex : 男
         * age : 23
         * onlinestatus : 1
         * constellation : 处女座
         * address : 黑龙江哈尔滨市市辖区
         * photo : ["http://video.520cai.cn/upload/ios/20170824/a612c36ef52104a18218b5eb4eb29041.png","http://video.520cai.cn/upload/ios/20170824/6d37a820737e895460a2fb300535187d.png","http://video.520cai.cn/upload/ios/20170824/385438af421204448fd311ea8294b6b7.png"]
         * listen_setting : 未设置
         * video_setting : 未设置
         * voice_setting : 未设置
         * signature : Hello world
         * labels : 可爱甜心,清新怡人,资深腐宅
         * commentnum : 0
         * rating : 100%
         * attentionnum : 1
         * totaltime : 21
         */

        private String video;
        private String voice;
        private String nickname;
        private String sex;
        private int age;
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
        private int attentionnum;
        private int totaltime;
        private List<String> photo;

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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

        public int getAttentionnum() {
            return attentionnum;
        }

        public void setAttentionnum(int attentionnum) {
            this.attentionnum = attentionnum;
        }

        public int getTotaltime() {
            return totaltime;
        }

        public void setTotaltime(int totaltime) {
            this.totaltime = totaltime;
        }

        public List<String> getPhoto() {
            return photo;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }
    }
}
