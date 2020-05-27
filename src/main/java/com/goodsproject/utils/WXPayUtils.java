package com.goodsproject.utils;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.goodsproject.Constant.PayConstant;
import com.goodsproject.config.WXConfig;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;


@Component
public class WXPayUtils {


    @Autowired
    private WXConfig wxPayConfig;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param outTradeNo 微信支付唯一标识符
     * @param orderId    订单id
     * @param amount     订单总价
     * @param bodyName   付款时名称
     * @return 二维码地址
     * @description 微信支付接口
     * @author suewong
     * @date 2020/4/13 15:26
     */
    public String unifiedOrder(String outTradeNo, String orderId, String bodyName, BigDecimal amount, String notifyUrl, String ip, String productId) throws Exception {
        Map<String, String> data = Maps.newHashMap();
        if (amount == null) {
            return null;
        }
        WXPay wxpay = new WXPay(wxPayConfig);
        data.put(PayConstant.WXRequestName.body, "谷子-" + bodyName);
        data.put(PayConstant.WXRequestName.detail, "详细信息");
        data.put(PayConstant.WXRequestName.out_trade_no, String.valueOf(outTradeNo));
        data.put(PayConstant.WXRequestName.device_info, "WEB");
        data.put(PayConstant.WXRequestName.fee_type, "CNY");
        Integer total = amount.multiply(BigDecimal.valueOf(100)).intValue();
        data.put(PayConstant.WXRequestName.total_fee, total + "");
        data.put(PayConstant.WXRequestName.spbill_create_ip, ip);
        data.put(PayConstant.WXRequestName.sign_type, WXPayConstants.MD5);
        data.put(PayConstant.WXRequestName.notify_url, notifyUrl);
        data.put(PayConstant.WXRequestName.product_id, productId);
        data.put(PayConstant.WXRequestName.nonce_str, WXPayUtil.generateNonceStr());
        data.put(PayConstant.WXRequestName.trade_type, "NATIVE"); // 此处指定为扫码支付data.put("product_id", product.getId() + "");


        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if (resp != null && resp.get("code_url") != null) {
                return resp.get("code_url");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param outTradeNo 微信支付唯一标识符
     * @param orderId    订单id
     * @param amount     订单总价
     * @param bodyName   付款时名称
     * @return 二维码地址
     * @description 微信支付接口
     * @author suewong
     * @date 2020/4/13 15:26
     */
    public String createOrder(String outTradeNo, String orderId, String bodyName, BigDecimal amount, String notifyUrl, String ip, String productId) throws Exception {
        Map<String, String> data = Maps.newHashMap();
        if (amount == null) {
            return null;
        }


        WXPay wxpay = new WXPay(wxPayConfig);
        data.put(PayConstant.WXRequestName.body, "爱回收-" + bodyName);
        data.put(PayConstant.WXRequestName.detail, "详细信息");
        data.put(PayConstant.WXRequestName.out_trade_no, String.valueOf(outTradeNo));
        data.put(PayConstant.WXRequestName.device_info, "WEB");
        data.put(PayConstant.WXRequestName.fee_type, "CNY");
        Integer total = amount.multiply(BigDecimal.valueOf(100)).intValue();
        data.put(PayConstant.WXRequestName.total_fee, total + "");
        data.put(PayConstant.WXRequestName.spbill_create_ip, ip);
        data.put(PayConstant.WXRequestName.sign_type, WXPayConstants.MD5);
        data.put(PayConstant.WXRequestName.notify_url, notifyUrl);
        data.put(PayConstant.WXRequestName.product_id, productId);
        String nonce_str = WXPayUtil.generateNonceStr();
        data.put(PayConstant.WXRequestName.nonce_str, nonce_str);
        data.put(PayConstant.WXRequestName.trade_type, "JSAPI");
        data.put(PayConstant.WXRequestName.openid, "oyI9a5OHZj5cUENePAumvjdmeO44");
        // 此处指定为扫码支付data.put("product_id", product.getId() + "");
        String a = "appid=" + wxPayConfig.getAppID() + "&mch_id=" + wxPayConfig.getMchID() + "&nonce_str=" + nonce_str;
        //拼接签名需要的参数
        //注：key为商户平台设置的密钥key
        String stringSignTemp = a + "&key=" + wxPayConfig.getApikey();
        stringSignTemp = MD5Utils.string2MD5(stringSignTemp).toUpperCase();
        data.put("sign", stringSignTemp);

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if (resp != null && resp.get("prepay_id") != null) {
                return resp.get("prepay_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
