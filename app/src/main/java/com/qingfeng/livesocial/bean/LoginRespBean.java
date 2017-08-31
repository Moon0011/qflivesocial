package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/23.
 */

public class LoginRespBean {


    /**
     * msg : yy
     * result : {"uid":29,"username":"18371458526","sign":"eJxNjV9PgzAUR79LXzXSUgrEZA91YoIZMZsjboSkIVDk4sYaKH*m8buLSNxez7nnd7-QdvV6l6Tpqa200Gcl0T3C6HbCkMlKQw6yHiFxqUMs5jLTnnWiFGQi0YLW2VXVZB9iUr*RhTGmDrOcWcpBQS1Fkuu-UcaYOZ7MtpN1A6dqFCYmjJgU44vUcJRTgi3iuMz*-wfvIw689dJ-5GXTRkOX0yPfcvIUGwdq8q78XKWbUPax0UMQNp6ODR7ylgMvNpHLnr3dw45ayu-66DCw9g3sdXETLPflSwGBT8-7Ou37xQJ9-wC9L1ls","islogin":1}
     */

    private String msg;
    private LoginInfoBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginInfoBean getResult() {
        return result;
    }

    public void setResult(LoginInfoBean result) {
        this.result = result;
    }

    public static class LoginInfoBean {
        /**
         * uid : 29
         * username : 18371458526
         * sign : eJxNjV9PgzAUR79LXzXSUgrEZA91YoIZMZsjboSkIVDk4sYaKH*m8buLSNxez7nnd7-QdvV6l6Tpqa200Gcl0T3C6HbCkMlKQw6yHiFxqUMs5jLTnnWiFGQi0YLW2VXVZB9iUr*RhTGmDrOcWcpBQS1Fkuu-UcaYOZ7MtpN1A6dqFCYmjJgU44vUcJRTgi3iuMz*-wfvIw689dJ-5GXTRkOX0yPfcvIUGwdq8q78XKWbUPax0UMQNp6ODR7ylgMvNpHLnr3dw45ayu-66DCw9g3sdXETLPflSwGBT8-7Ou37xQJ9-wC9L1ls
         * islogin : 1
         */

        private int uid;
        private String username;
        private String sign;
        private int islogin;

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

        public int getIslogin() {
            return islogin;
        }

        public void setIslogin(int islogin) {
            this.islogin = islogin;
        }
    }
}
