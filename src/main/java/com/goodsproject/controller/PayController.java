package com.goodsproject.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.goodsproject.service.PayService;
import com.goodsproject.utils.HttpsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@RestController
@Slf4j
public class PayController {


    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public String payRequest(HttpServletRequest request) {

        return payService.payByWeixin(10000, null, BigDecimal.valueOf(0.01),
                "http://47.105.133.58:8011/payResponse", "测试",
                HttpsUtils.getIpAddress(request), "保证金");

    }


    @RequestMapping("/payResponse")
    public String payResponse(HttpServletResponse response, HttpServletRequest request) {

        log.info("支付回调" + request);
        return "success";
    }

}
