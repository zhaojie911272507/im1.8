package com.imooc3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration  /*要容器扫描到*/
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean  /*该类是一个过滤器*/
    public CorsFilter corsFilter(){
        //1、添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");/*该方法就是添加允许的跨域内容，这里添加一个前端请求端，这里不建议使用 * ，会有安全问题*/

        //设置是否发送cookie信息
        config.setAllowCredentials(true);   /*是否允许请求携带一些相应的内容，like cookie*/

        //设置允许请求的方式
        config.addAllowedMethod("*");

        //设置允许的header
        config.addAllowedHeader("*");

        //2、为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);
        //3.返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
