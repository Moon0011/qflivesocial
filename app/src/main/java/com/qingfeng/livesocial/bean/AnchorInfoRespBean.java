package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class AnchorInfoRespBean {

    /**
     * msg : y
     * result : {"anchorpic":"http://video.520cai.cn","nickname":"未设置","signature":"未设置","labelinfo":"未设置","address":"未设置","birthday":"未设置","sex":null,"constellation":"未设置"}
     */

    private String msg;
    private AnchorInfoBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AnchorInfoBean getResult() {
        return result;
    }

    public void setResult(AnchorInfoBean result) {
        this.result = result;
    }

    public static class AnchorInfoBean {
        /**
         * anchorpic : http://video.520cai.cn
         * nickname : 未设置
         * signature : 未设置
         * labelinfo : 未设置
         * address : 未设置
         * birthday : 未设置
         * sex : null
         * constellation : 未设置
         */

        private String anchorpic;
        private String nickname;
        private String signature;
        private String labelinfo;
        private String address;
        private String birthday;
        private String sex;
        private String constellation;

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

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getLabelinfo() {
            return labelinfo;
        }

        public void setLabelinfo(String labelinfo) {
            this.labelinfo = labelinfo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }
    }
}
