package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/23.
 */

public class LoginRespBean {

    /**
     * msg : y
     * result : {"uid":11,"username":"18601230114","sign":"eJxNjV1vgjAYRv9Lb11mCy3YJbvwi8WgUnSyLDFpOihaDZVBB5pl-32ImO32nPe8zzd4na8fRRyfvrTh5pJL8AQgeGixSqQ2KlWyaCAaOBBZNkQId1rkuUq4MNwukn9VmRx5q64RhhDaLsFuJ*U5V4XkIjW3p4QQqznpbCWLUp10IyyIyHUN-kmjMtkm0HIpoY5931O7Bi*mm-EsnGymg0mPZb7e9oOxobon8TFilifY4SUOs2jbf-PmoygYZTWtZ7vgsj*sl04deGkYfYq4StmyKh1vj32xosOPYSB89r5Cmbt4Bj*-D9lX5g__"}
     */

    private String msg;
    private LoginRespData result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginRespData getResult() {
        return result;
    }

    public void setResult(LoginRespData result) {
        this.result = result;
    }

    public static class LoginRespData {
        /**
         * uid : 11
         * username : 18601230114
         * sign : eJxNjV1vgjAYRv9Lb11mCy3YJbvwi8WgUnSyLDFpOihaDZVBB5pl-32ImO32nPe8zzd4na8fRRyfvrTh5pJL8AQgeGixSqQ2KlWyaCAaOBBZNkQId1rkuUq4MNwukn9VmRx5q64RhhDaLsFuJ*U5V4XkIjW3p4QQqznpbCWLUp10IyyIyHUN-kmjMtkm0HIpoY5931O7Bi*mm-EsnGymg0mPZb7e9oOxobon8TFilifY4SUOs2jbf-PmoygYZTWtZ7vgsj*sl04deGkYfYq4StmyKh1vj32xosOPYSB89r5Cmbt4Bj*-D9lX5g__
         */

        private int uid;
        private String username;
        private String sign;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
