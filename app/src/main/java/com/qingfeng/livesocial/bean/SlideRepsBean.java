package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SlideRepsBean {

    /**
     * msg : y
     * result : [{"id":2,"title":"这是一个测试","url":"","pic":"http://video.520cai.cn/upload/zhibo/20170809/79b6c191b51d14fdf1d17f6ed35666b7.jpg","status":1,"order":0},{"id":4,"title":"1111","url":"111111111","pic":"http://video.520cai.cn/upload/zhibo/20170817/9a8aa0552177cee4e464de23ebc0aa39.jpg","status":1,"order":1},{"id":5,"title":"11111111111","url":"111111","pic":"http://video.520cai.cn/upload/zhibo/20170817/48352dc33a246b55e97db528e3c67dd1.jpg","status":1,"order":0},{"id":6,"title":"11","url":"11","pic":"http://video.520cai.cn/upload/zhibo/20170817/a7c7fa9fa53429bac09861f09c7064a5.jpg","status":1,"order":0}]
     */

    private String msg;
    private List<SlideBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SlideBean> getResult() {
        return result;
    }

    public void setResult(List<SlideBean> result) {
        this.result = result;
    }

    public static class SlideBean {
        /**
         * id : 2
         * title : 这是一个测试
         * url :
         * pic : http://video.520cai.cn/upload/zhibo/20170809/79b6c191b51d14fdf1d17f6ed35666b7.jpg
         * status : 1
         * order : 0
         */

        private int id;
        private String title;
        private String url;
        private String pic;
        private int status;
        private int order;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }
}
