package com.goodsproject.service.impl;

import com.goodsproject.model.User;
import com.goodsproject.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean register(String username, String password, Integer qq) {
        return false;
    }

    @Override
    public User queryUserById(Integer userId) {
        return null;
    }
}
