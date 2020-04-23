package com.goodsproject.service;

import com.goodsproject.model.User;

public interface UserService {


    /**
     * @param userName 用户名
     * @param password 密码
     * @return boolean
     * @description 用户登录接口
     * @author suewong
     * @date 2020/4/13 11:37
     */
    boolean login(String userName, String password);


    /**
     * @param username 用户设置的登录用户名（唯一）
     * @param password 用户密码
     * @param qq       QQ号（唯一）
     * @return boolean
     * @description 用户注册接口
     * @author suewong
     * @date 2020/4/13 11:39
     */
    boolean register(String username, String password, Integer qq);

    /**
     * @param userId 用户的主键id
     * @return
     * @description 根据l用户的id查询用户信息
     * @author suewong
     * @date 2020/4/13 11:41
     */
    User queryUserById(Integer userId);
}
