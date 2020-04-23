package com.goodsproject.service.impl;

import com.goodsproject.Constant.PayConstant;
import com.goodsproject.mapper.PayDetailMapper;
import com.goodsproject.model.PayDetail;
import com.goodsproject.service.PayService;
import com.goodsproject.utils.WXPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class PayServiceImpl implements PayService {


    @Autowired
    private WXPayUtils wxPayUtils;
    @Autowired
    private PayDetailMapper detailMapper;

    @Override
    public String payByWeixin(Integer userId, String orderId, BigDecimal amount, String notifyUrl, String description, String ip, String orderName) {


        try {

            String outTradeNo = System.currentTimeMillis() + "";

            PayDetail payDetail = new PayDetail();
            payDetail.setIp(ip);
            payDetail.setSource(PayConstant.Source.PC);
            payDetail.setType(PayConstant.Type.wxPay);
            payDetail.setDescription(description);
            payDetail.setOrderId(orderId);
            payDetail.setStatus(PayConstant.Status.noPay);
            payDetail.setMoney(amount);
            payDetail.setReturnUrl(notifyUrl);
            payDetail.setUserId(userId);
            payDetail.setOutTradeNo(outTradeNo);
            if (detailMapper.insertSelective(payDetail) > 0) {
                return wxPayUtils.unifiedOrder(outTradeNo, orderId, orderName, amount, notifyUrl, ip);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
