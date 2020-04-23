package com.goodsproject.Entity;

/**
 * @author suewong
 * @description controller 返回类
 * @date 2020/4/13 16:10
 */
public class RespEntity {


    private Class T;

    private CodeEnum code;


    public RespEntity(Class t, CodeEnum code) {
        T = t;
        this.code = code;
    }

    public RespEntity(CodeEnum code) {
        this.code = code;
    }



}
