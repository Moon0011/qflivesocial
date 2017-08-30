package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class PersonalCenterRespBean {

    /**
     * msg : y
     * result : {"anchorpic":"http://video.520cai.cn","roompic":"http://video.520cai.cn","nickname":null,"curroomnum":1588439941,"signature":"未设置","age":"未设置","sex":null,"photo":"未设置"}
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
         * anchorpic : http://video.520cai.cn
         * roompic : http://video.520cai.cn
         * nickname : null
         * curroomnum : 1588439941
         * signature : 未设置
         * age : 未设置
         * sex : null
         * photo : 未设置
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
