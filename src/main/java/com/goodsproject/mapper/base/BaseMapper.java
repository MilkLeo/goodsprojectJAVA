package com.goodsproject.mapper.base;


public interface BaseMapper<T> {

    int deleteByPrimaryKey(T var);

    int insert(T var);

    int insertSelective(T var);

    T selectByPrimaryKey(T var);

    int updateByPrimaryKeySelective(T var);

    int updateByPrimaryKey(T var);
}
