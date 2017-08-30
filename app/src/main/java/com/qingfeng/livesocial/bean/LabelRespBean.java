package com.qingfeng.livesocial.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class LabelRespBean {

    /**
     * msg : y
     * result : [{"id":2,"content":"旅游达人","type":1},{"id":5,"content":"暖心治愈","type":1},{"id":6,"content":"运动健将","type":1},{"id":7,"content":"文艺范儿","type":1},{"id":8,"content":"可爱甜心","type":1},{"id":9,"content":"清新怡人","type":1},{"id":10,"content":"呆萌萝莉","type":1},{"id":11,"content":"资深腐宅","type":1},{"id":12,"content":"典雅古风","type":1},{"id":13,"content":"强气御姐","type":1}]
     */

    private String msg;
    private List<LabelBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<LabelBean> getResult() {
        return result;
    }

    public void setResult(List<LabelBean> result) {
        this.result = result;
    }

    public static class LabelBean {
        /**
         * id : 2
         * content : 旅游达人
         * type : 1
         */

        private int id;
        private String content;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
