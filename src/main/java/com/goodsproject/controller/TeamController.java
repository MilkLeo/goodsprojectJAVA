package com.goodsproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TeamController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(){
        return "success";
    }
}
