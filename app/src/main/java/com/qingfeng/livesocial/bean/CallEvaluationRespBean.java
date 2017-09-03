package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CallEvaluationRespBean {


    /**
     * msg : y
     * result : {"commentinfo":[{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299109,"label":"2,3"},{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299113,"label":"2,3"}],"labelinfo":[{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299109,"label":"2,3"},{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299113,"label":"2,3"}],"userinfo":{"anchorpic":"http://video.520cai.cn/upload/ios/20170901/50458754c45969429ac2f6220c3405b4.png","roompic":"/upload/ios/20170823/755697057348e5da0e8ae7e2854dc535.png","nickname":"马卿","curroomnum":1077789306,"sex":"男","age":3,"signature":"世上无难事 ，只怕有心人。"}}
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
         * commentinfo : [{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299109,"label":"2,3"},{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299113,"label":"2,3"}]
         * labelinfo : [{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299109,"label":"2,3"},{"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","content":"111111","starnum":4,"creattime":1503299113,"label":"2,3"}]
         * userinfo : {"anchorpic":"http://video.520cai.cn/upload/ios/20170901/50458754c45969429ac2f6220c3405b4.png","roompic":"/upload/ios/20170823/755697057348e5da0e8ae7e2854dc535.png","nickname":"马卿","curroomnum":1077789306,"sex":"男","age":3,"signature":"世上无难事 ，只怕有心人。"}
         */

        private UserinfoBean userinfo;
        private List<CommentinfoBean> commentinfo;
        private List<LabelinfoBean> labelinfo;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public List<CommentinfoBean> getCommentinfo() {
            return commentinfo;
        }

        public void setCommentinfo(List<CommentinfoBean> commentinfo) {
            this.commentinfo = commentinfo;
        }

        public List<LabelinfoBean> getLabelinfo() {
            return labelinfo;
        }

        public void setLabelinfo(List<LabelinfoBean> labelinfo) {
            this.labelinfo = labelinfo;
        }

        public static class UserinfoBean {
            /**
             * anchorpic : http://video.520cai.cn/upload/ios/20170901/50458754c45969429ac2f6220c3405b4.png
             * roompic : /upload/ios/20170823/755697057348e5da0e8ae7e2854dc535.png
             * nickname : 马卿
             * curroomnum : 1077789306
             * sex : 男
             * age : 3
             * signature : 世上无难事 ，只怕有心人。
             */

            private String anchorpic;
            private String roompic;
            private String nickname;
            private int curroomnum;
            private String sex;
            private int age;
            private String signature;

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

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }
        }

        public static class CommentinfoBean {
            /**
             * anchorpic : http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png
             * nickname : 11
             * content : 111111
             * starnum : 4
             * creattime : 1503299109
             * label : 2,3
             */

            private String anchorpic;
            private String nickname;
            private String content;
            private int starnum;
            private long creattime;
            private String label;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStarnum() {
                return starnum;
            }

            public void setStarnum(int starnum) {
                this.starnum = starnum;
            }

            public long getCreattime() {
                return creattime;
            }

            public void setCreattime(long creattime) {
                this.creattime = creattime;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }

        public static class LabelinfoBean {
            /**
             * anchorpic : http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png
             * nickname : 11
             * content : 111111
             * starnum : 4
             * creattime : 1503299109
             * label : 2,3
             */

            private String anchorpic;
            private String nickname;
            private String content;
            private int starnum;
            private int creattime;
            private String label;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStarnum() {
                return starnum;
            }

            public void setStarnum(int starnum) {
                this.starnum = starnum;
            }

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }
}
