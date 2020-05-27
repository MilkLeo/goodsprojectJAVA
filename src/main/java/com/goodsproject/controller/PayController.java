package com.goodsproject.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.goodsproject.Constant.PayConstant;
import com.goodsproject.service.PayService;
import com.goodsproject.utils.HttpsUtils;
import com.goodsproject.utils.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@Slf4j
public class PayController {


    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public void payRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {

        String content = payService.payByWeixin(10000, null, BigDecimal.valueOf(0.01),
                "http://47.105.133.58:8011/payResponse", "测试",
                HttpsUtils.getIpAddress(request), "保证金");

        InputStream in= QRCodeUtils.buildQuickMark(content);

        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        //创建存放文件内容的数组
        byte[] buff =new byte[1024];
        //所读取的内容使用n来接收
        int n;
        //当没有读取完时,继续读取,循环
        while((n=in.read(buff))!=-1){
            //将字节数组的数据全部写入到输出流中
            outputStream.write(buff,0,n);
        }
        //强制将缓存区的数据进行输出
        outputStream.flush();
        //关流
        outputStream.close();
        in.close();

    }


    @RequestMapping("/payResponse")
    public String payResponse(HttpServletResponse response, HttpServletRequest request) {


        try {
            if (payService.returnWXPay(request)) {
                return PayConstant.WXreturnParam.success;
            }
        } catch (Exception e) {

            log.info(e.getMessage());
            return PayConstant.WXreturnParam.error;
        }
        return PayConstant.WXreturnParam.error;
    }

}
