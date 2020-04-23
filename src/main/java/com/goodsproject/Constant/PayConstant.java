package com.goodsproject.Constant;

public class PayConstant {

    /**
     * @author suewong
     * @description 支付相关常数
     * @date 2020/4/23 9:32
     */
    public static class Source {

        //微信小程序
        public static Integer WXApp = 0;
        //pc电脑
        public static Integer PC = 1;

    }


    public static class Status {
        //未支付
        public static Integer noPay = 0;
        //已支付
        public static Integer Payed = 1;
    }


    public static class Type {
        //支付宝支付
        public static Integer aliPay = 0;
        //微信支付
        public static Integer wxPay = 1;
    }
}
