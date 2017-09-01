package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class PersonalCenterRespBean {


    /**
     * msg : y
     * result : {"anchorpic":"http://video.520cai.cn/upload/ios/20170831/074a05d10cd3ff57960bd0c15940903f.png","roompic":"http://video.520cai.cn/upload/ios/20170831/074a05d10cd3ff57960bd0c15940903f.png","nickname":"马卿","curroomnum":1077789306,"signature":"世上无难事 ，只怕有心人。","age":3,"sex":"男","photo":[{"picurl":"http://video.520cai.cn/upload/ios/20170823/755697057348e5da0e8ae7e2854dc535.png","id":3},{"picurl":"http://video.520cai.cn/upload/ios/20170823/bd6fc6daf626173562be5816d581d948.png","id":4},{"picurl":"http://video.520cai.cn/upload/ios/20170823/6bfd9de1619339b881aa1dbb4e70ff8f.png","id":5},{"picurl":"http://video.520cai.cn/upload/ios/20170823/491f753a9e094a56541935fbe87f7283.png","id":6},{"picurl":"http://video.520cai.cn/upload/ios/20170823/491f753a9e094a56541935fbe87f7283.png","id":7}]}
     */

    private String msg;
    private PersonalCenterBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PersonalCenterBean getResult() {
        return result;
    }

    public void setResult(PersonalCenterBean result) {
        this.result = result;
    }

    public static class PersonalCenterBean {
        /**
         * anchorpic : http://video.520cai.cn/upload/ios/20170831/074a05d10cd3ff57960bd0c15940903f.png
         * roompic : http://video.520cai.cn/upload/ios/20170831/074a05d10cd3ff57960bd0c15940903f.png
         * nickname : 马卿
         * curroomnum : 1077789306
         * signature : 世上无难事 ，只怕有心人。
         * age : 3
         * sex : 男
         * photo : [{"picurl":"http://video.520cai.cn/upload/ios/20170823/755697057348e5da0e8ae7e2854dc535.png","id":3},{"picurl":"http://video.520cai.cn/upload/ios/20170823/bd6fc6daf626173562be5816d581d948.png","id":4},{"picurl":"http://video.520cai.cn/upload/ios/20170823/6bfd9de1619339b881aa1dbb4e70ff8f.png","id":5},{"picurl":"http://video.520cai.cn/upload/ios/20170823/491f753a9e094a56541935fbe87f7283.png","id":6},{"picurl":"http://video.520cai.cn/upload/ios/20170823/491f753a9e094a56541935fbe87f7283.png","id":7}]
         */

        private String anchorpic;
        private String roompic;
        private String nickname;
        private int curroomnum;
        private String signature;
        private String age;
        private String sex;
        private List<PhotoBean> photo;

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

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
        }

        public static class PhotoBean {
            /**
             * picurl : http://video.520cai.cn/upload/ios/20170823/755697057348e5da0e8ae7e2854dc535.png
             * id : 3
             */

            private String picurl;
            private int id;

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
