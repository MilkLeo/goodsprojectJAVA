package com.goodsproject.mapper;

import com.goodsproject.mapper.base.BaseMapper;
import com.goodsproject.model.PayDetail;
import org.apache.ibatis.annotations.Mapper;


public interface PayDetailMapper extends BaseMapper<PayDetail> {


    Long insertAndGetId(PayDetail payDetail);


    int updateByoutTradeNo(PayDetail payDetail);

}