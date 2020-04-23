package com.goodsproject.mapper.base;

import com.goodsproject.model.User;

public interface BaseMapper<T> {

    int deleteByPrimaryKey(T var);

    int insert(T var);

    int insertSelective(T var);

    User selectByPrimaryKey(T var);

    int updateByPrimaryKeySelective(T var);

    int updateByPrimaryKey(T var);
}
