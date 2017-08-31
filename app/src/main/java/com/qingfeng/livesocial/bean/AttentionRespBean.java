package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class AttentionRespBean  {

    /**
     * msg : y
     * result : [{"nickname":"测试","anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/895542cc7037f724b31ea5ad69ad58a1.jpg","sex":"女","age":null,"signture":"111"},{"nickname":"11","anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","sex":"女","age":null,"signture":"1111"}]
     */

    private String msg;
    private List<AttentionBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AttentionBean> getResult() {
        return result;
    }

    public void setResult(List<AttentionBean> result) {
        this.result = result;
    }

    public static class AttentionBean {
        /**
         * nickname : 测试
         * anchorpic : http://video.520cai.cn/upload/zhibo/20170808/895542cc7037f724b31ea5ad69ad58a1.jpg
         * sex : 女
         * age : null
         * signture : 111
         */

        private String nickname;
        private String anchorpic;
        private String sex;
        private Object age;
        private String signture;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAnchorpic() {
            return anchorpic;
        }

        public void setAnchorpic(String anchorpic) {
            this.anchorpic = anchorpic;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getSignture() {
            return signture;
        }

        public void setSignture(String signture) {
            this.signture = signture;
        }
    }
}
