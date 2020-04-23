package com.goodsproject.utils;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
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
    public String unifiedOrder(String outTradeNo, String orderId, String bodyName, BigDecimal amount, String notifyUrl, String ip) throws Exception {
        Map<String, String> data = Maps.newHashMap();
        if (amount == null) {
            return null;
        }
        WXPay wxpay = new WXPay(wxPayConfig);
        data.put("body", "XX-" + bodyName);
        data.put("detail", "详细信息");
        data.put("out_trade_no", String.valueOf(outTradeNo));
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        Integer total = amount.multiply(BigDecimal.valueOf(100)).intValue();
        data.put("total_fee", total + "");
        data.put("spbill_create_ip", ip);
        data.put("sign_type", WXPayConstants.MD5);
        data.put("notify_url", notifyUrl);
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("trade_type", "NATIVE"); // 此处指定为扫码支付data.put("product_id", product.getId() + "");


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

}
