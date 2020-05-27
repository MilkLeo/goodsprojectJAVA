package com.goodsproject.Constant;

/**
 * @author suewong
 * @description 支付相关常数
 * @date 2020/4/23 9:32
 */
public class PayConstant {

    /**
     * @author suewong
     * @description 支付来源
     * @date 2020/4/24 10:43
     */
    public static class Source {

        //微信小程序
        public static Integer WXApp = 0;
        //pc电脑
        public static Integer PC = 1;

    }

    /**
     * @author suewong
     * @description 支付状态
     * @date 2020/4/24 10:43
     */
    public static class Status {
        //未支付
        public static Integer noPay = 0;
        //已支付
        public static Integer Payed = 1;
    }

    /**
     * @author suewong
     * @description 支付方式
     * @date 2020/4/24 10:43
     */
    public static class Type {
        //支付宝支付
        public static Integer aliPay = 0;
        //微信支付
        public static Integer wxPay = 1;
    }


    /**
     * @author suewong
     * @description 微信支付后的返回参数名
     * @date 2020/4/24 10:43
     */
    public static class WXReturnName {

        public static String return_code = "return_code";

        public static String return_msg = "return_msg";

        public static String out_trade_no = "out_trade_no";

        public static String openid = "openid";

        public static String mch_id = "mch_id";

        public static String is_subscribe = "is_subscribe";

        public static String total_fee = "total_fee";

        public static String transaction_id = "transaction_id";

        public static String product_id = "product_id";
    }

    /**
     * @author suewong
     * @description 微信支付请求参数
     * @date 2020/4/24 10:44
     */
    public static class WXRequestName {

        public static String body = "body";

        public static String detail = "detail";

        public static String out_trade_no = "out_trade_no";

        public static String device_info = "device_info";

        public static String fee_type = "fee_type";

        public static String spbill_create_ip = "spbill_create_ip";

        public static String total_fee = "total_fee";

        public static String sign_type = "sign_type";

        public static String notify_url = "notify_url";

        public static String trade_type = "trade_type";

        public static String nonce_str = "nonce_str";

        public static String product_id = "product_id";

        public static String openid = "openid";
    }


    /**
     * @author suewong
     * @description 微信回调后，服务器返回微信的信息
     * @date 2020/4/24 10:48
     */
    public static class WXreturnParam {


        public static String success = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

        public static String error = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg>" +
                "<![CDATA[报文为空]]></return_msg></xml>";
    }

    /**
     * @author suewong
     * @description 微信支付成功或失败结果
     * @date 2020/4/24 11:03
     */
    public static class WXPayReturnCode {


        public static String success = "SUCCESS";

        public static String fail = "FAIL";

    }
}
