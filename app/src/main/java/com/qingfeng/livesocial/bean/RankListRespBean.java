package com.qingfeng.livesocial.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */

public class RankListRespBean implements Serializable{


    /**
     * msg : y
     * result : [{"uid":1,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170809/79b6c191b51d14fdf1d17f6ed35666b7.jpg","nickname":"测试","attentionnum":"0"},{"uid":2,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170808/39330d2f5c6dfc7f342cbfd0a5d2e3d1.png","nickname":"11","attentionnum":"0"},{"uid":3,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"测试1","attentionnum":"0"},{"uid":4,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/9a8aa0552177cee4e464de23ebc0aa39.jpg","nickname":"测试","attentionnum":"0"},{"uid":5,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/a7c7fa9fa53429bac09861f09c7064a5.jpg","nickname":"测试3","attentionnum":"0"},{"uid":6,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/9a8aa0552177cee4e464de23ebc0aa39.jpg","nickname":"测试4","attentionnum":"0"},{"uid":21,"anchorpic":"http://video.520cai.cn/upload/ios/20170824/a5072309945e04f51f15ea5f4d543f94.png","nickname":"Good","attentionnum":"0"},{"uid":23,"anchorpic":"http://video.520cai.cn/upload/ios/20170822/c4718f6fbbc3e836ac5e18b5b70c8008.png","nickname":"Ggg","attentionnum":"0"},{"uid":27,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170824/1cb7d22ee9704cccfc3b78f3c476c119.png","nickname":"干妹妹还是干妹妹","attentionnum":"0"},{"uid":28,"anchorpic":"http://video.520cai.cn/upload/zhibo/20170817/48352dc33a246b55e97db528e3c67dd1.jpg","nickname":"cai","attentionnum":"0"},{"uid":29,"anchorpic":"http://video.520cai.cn","nickname":null,"attentionnum":"0"}]
     */

    private String msg;
    private List<RanklistBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RanklistBean> getResult() {
        return result;
    }

    public void setResult(List<RanklistBean> result) {
        this.result = result;
    }

    public static class RanklistBean implements Serializable{
        /**
         * uid : 1
         * anchorpic : http://video.520cai.cn/upload/zhibo/20170809/79b6c191b51d14fdf1d17f6ed35666b7.jpg
         * nickname : 测试
         * attentionnum : 0
         */

        private int uid;
        private String anchorpic;
        private String nickname;
        private String attentionnum;

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

        public String getAttentionnum() {
            return attentionnum;
        }

        public void setAttentionnum(String attentionnum) {
            this.attentionnum = attentionnum;
        }
    }
}
