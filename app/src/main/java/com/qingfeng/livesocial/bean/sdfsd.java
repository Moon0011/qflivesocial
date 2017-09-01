package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/31.
 */

public class sdfsd {


    /**
     * msg : y
     * result : {"anchorpic":"http://video.520cai.cn/upload/ios/20170831/9b90731c60b4bd5921ee3a3bf1deceac.png","roompic":"http://video.520cai.cn/upload/ios/20170901/e4aefb167112e217a4fab771c5b58abe.png","nickname":"ximing","curroomnum":1588439941,"signature":"哦哦哦哦哦哦哦哦","age":"0","sex":"m","photo":"[]"}
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
         * anchorpic : http://video.520cai.cn/upload/ios/20170831/9b90731c60b4bd5921ee3a3bf1deceac.png
         * roompic : http://video.520cai.cn/upload/ios/20170901/e4aefb167112e217a4fab771c5b58abe.png
         * nickname : ximing
         * curroomnum : 1588439941
         * signature : 哦哦哦哦哦哦哦哦
         * age : 0
         * sex : m
         * photo : []
         */

        private String anchorpic;
        private String roompic;
        private String nickname;
        private int curroomnum;
        private String signature;
        private String age;
        private String sex;
        private String photo;

        public String getAnchorpic() {
            return anchorpic;
        }

        public void setAnchorpic(String anchorpic) {
            this.anchorpic = anchorpic;
        }

        public String getRoompic() {
            return roompic;
        }

        public void setRoompic(String roompic) {
            this.roompic = roompic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getCurroomnum() {
            return curroomnum;
        }

        public void setCurroomnum(int curroomnum) {
            this.curroomnum = curroomnum;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
