package com.imooc3;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application{//启动项目的类

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}