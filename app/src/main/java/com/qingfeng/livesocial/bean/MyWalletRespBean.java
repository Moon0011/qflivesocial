package com.qingfeng.livesocial.bean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class MyWalletRespBean  {

    /**
     * msg : y
     * result : {"daycoins":0,"monthcoins":0,"balance":0}
     */

    private String msg;
    private MyWalletBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MyWalletBean getResult() {
        return result;
    }

    public void setResult(MyWalletBean result) {
        this.result = result;
    }

    public static class MyWalletBean {
        /**
         * daycoins : 0
         * monthcoins : 0
         * balance : 0
         */

        private int daycoins;
        private int monthcoins;
        private int balance;

        public int getDaycoins() {
            return daycoins;
        }

        public void setDaycoins(int daycoins) {
            this.daycoins = daycoins;
        }

        public int getMonthcoins() {
            return monthcoins;
        }

        public void setMonthcoins(int monthcoins) {
            this.monthcoins = monthcoins;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
