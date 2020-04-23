package com.goodsproject.model;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String pictureUrl;

    private Integer isvalid;

    private Integer qq;
}