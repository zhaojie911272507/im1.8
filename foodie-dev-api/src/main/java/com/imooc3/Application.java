package com.imooc3;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.imooc3.mapper")/*扫描mybatis通用mapper所在的包*/
@ComponentScan(basePackages = {"com.imooc3","org.n3r.idworker"})/*扫描所有包以及相关组件包；要被容器扫描到*/
@EnableScheduling
public class Application{//启动项目的类

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}