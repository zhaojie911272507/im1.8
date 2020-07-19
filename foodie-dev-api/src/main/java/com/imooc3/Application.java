package com.imooc3;



import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.imooc3.mapper")/*扫描mybatis通用mapper所在的包*/
public class Application{//启动项目的类

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}