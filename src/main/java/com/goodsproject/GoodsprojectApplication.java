package com.goodsproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@MapperScan(value = "com.goodsproject.mapper")
@SpringBootApplication
public class GoodsprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsprojectApplication.class, args);
    }

}
