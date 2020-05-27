package com.goodsproject.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayDetail {
    private Long id;

    private String orderId;

    private String description;

    private Integer type;

    private String ip;

    private Integer status;

    private BigDecimal money;

    private String returnUrl;

    private Integer source;

    private Integer userId;

    private String outTradeNo;

    private Date createDt;

    private Date updateDt;

    private Date payTime;

    private String transactionId;

    private String openId;
}