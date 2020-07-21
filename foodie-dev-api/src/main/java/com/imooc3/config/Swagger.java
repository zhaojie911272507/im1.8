package com.imooc3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration/*配置要被扫描到就要加上*/
@EnableSwagger2  /*开启Swagger2配置*/
public class Swagger {

    //文档访问地址：http:// localhost:8088/swagger-ui.html         官方原路径
    //文档访问地址：http:// localhost:8088/doc.html        另一个

    //配置swgger2核心配置  docket
    @Bean       /*要加上bean注解，加上以后spring才知道它是一个类*/
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)//指定api类型为swagger_2
                             .apiInfo(apiInfo2())          //用于定义api文档
                             .select()
                             .apis(RequestHandlerSelectors
                                        .basePackage("com.imooc3.controller")) //指定controller包，扫描包所在的地址，也就是现有所有的Controller所在的地址
                             .paths(PathSelectors.any())    //选择所有的controller
                             .build();
    }

    private ApiInfo apiInfo2(){
        return new ApiInfoBuilder()
                .title("天天爱吃  电商平台接口api")   //文档页标签
                .contact(new Contact("mall",
                                    "http://www.***.com",
                                     "zj12november@foxmail.com"   ))   //联系人信息
                .description("天天爱吃")  //详细信息
                .version("1.0.1")       //文档版本号
                .termsOfServiceUrl("http://www.***.com")    //网站地址
                .build();

    }
}
