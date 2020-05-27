package com.goodsproject.service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface PayService {

    /**
     * @param userId      当前用户id
     * @param orderId     订单id
     * @param amount      金额
     * @param notifyUrl   支付回调地址
     * @param description 订单描述
     * @param ip          当前用户的ip地址
     * @param orderName   订单名称
     * @return 二维码页面
     * @description 微信扫码支付
     * @author suewong
     * @date 2020/4/14 10:37
     */
    String payByWeixin(Integer userId, String orderId, BigDecimal amount, String notifyUrl, String description, String ip, String orderName);

    /**
     * @param request
     * @return boolean  true 支付成功 false 支付失败
     * @description 微信回调返回参数
     * @author suewong
     * @date 2020/4/23 15:07
     */
    boolean returnWXPay(HttpServletRequest request) throws Exception;




}
