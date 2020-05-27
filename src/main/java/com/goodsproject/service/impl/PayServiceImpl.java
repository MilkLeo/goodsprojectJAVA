package com.goodsproject.service.impl;

import com.github.wxpay.sdk.WXPayConstants;
import com.goodsproject.Constant.PayConstant;
import com.goodsproject.mapper.PayDetailMapper;
import com.goodsproject.model.PayDetail;
import com.goodsproject.service.PayService;
import com.goodsproject.utils.QRCodeUtils;
import com.goodsproject.utils.WXPayUtils;
import com.goodsproject.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
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
            Long payId = detailMapper.insertAndGetId(payDetail);
            if (payId != null) {
                return wxPayUtils.unifiedOrder(outTradeNo, String.valueOf(payId), orderName, amount, notifyUrl, ip, String.valueOf(payId));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public boolean returnWXPay(HttpServletRequest request) throws Exception {
        //读取参数
        ServletInputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        log.info(sb.toString());
        Map<String, String> map = XmlUtils.doXMLParse(sb.toString());
        String returnCode = map.get(PayConstant.WXReturnName.return_code);
        //校验一下
        if (!StringUtils.isEmpty(returnCode) && returnCode.equals(PayConstant.WXPayReturnCode.success)) {
            //商户订单号
            String outTradeNo = map.get(PayConstant.WXReturnName.out_trade_no);
            log.info("outTradeNo : " + outTradeNo);
            //微信支付订单号
            String transactionId = map.get(PayConstant.WXReturnName.transaction_id);
            log.info("transaction_id : " + transactionId);
            //支付完成时间
            SimpleDateFormat payFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date payDate = payFormat.parse(map.get("time_end"));
            SimpleDateFormat systemFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info("支付时间：" + systemFormat.format(payDate));

            String openId = map.get(PayConstant.WXReturnName.openid);
            PayDetail payDetail = new PayDetail();
            payDetail.setPayTime(payDate);
            payDetail.setTransactionId(transactionId);
            payDetail.setOutTradeNo(outTradeNo);
            payDetail.setStatus(PayConstant.Status.Payed);
            payDetail.setOpenId(openId);
            if (detailMapper.updateByoutTradeNo(payDetail) > 0) {
                return true;
            }
            return false;

        }


        return false;
    }
}
