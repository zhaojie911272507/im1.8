package com.imooc3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
@RestController
public class HelloController {

    final  static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public Object hello(){

        logger.debug("debug: hello~");
        logger.info("info: hello~");/*2s一下*/
        logger.warn("warn: hello~");/*2s*/
        logger.error("error: hello~");/*3s*/

        return "hello Mapper";//直接返还出去
    }

    @GetMapping("/setSession")
    public Object setSession (HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userInfo","new user");
        session.setMaxInactiveInterval(3600);
        session.getAttribute("userInfo");
//        session.removeAttribute("userInfo");
        return "ok";
    }
}
