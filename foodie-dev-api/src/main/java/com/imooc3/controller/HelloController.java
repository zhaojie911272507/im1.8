package com.imooc3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Object hello(){
        return "hello World";//直接返还出去
    }
}
