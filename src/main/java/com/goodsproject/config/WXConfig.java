package com.goodsproject.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Data
public class WXConfig implements WXPayConfig {

    /**
     * 商家企业号
     */
    @Value("${wx.pay.appid}")
    private  String appid;
    /**
     * 商户号
     */
    @Value("${wx.pay.mchid}")
    private  String mchid;

    /**
     * 商户支付密钥
     */
    @Value("${wx.pay.apikey}")
    private  String apikey;

    @Override
    public String getAppID() {
        return appid;
    }

    @Override
    public String getMchID() {
        return mchid;
    }

    @Override
    public String getKey() {
        return apikey;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
