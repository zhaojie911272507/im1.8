package com.imooc3.controller;

import com.imooc3.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//@Controller
@ApiIgnore      /*不在接口文档里显示*/
@RestController
public class StufooController {
    @Autowired
    private StuService stuService;

    @GetMapping("/getStu")
    public Object getStu(int id){
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    public Object saveStu(){
         stuService.saveStu();
         return "OK";
    }

    @PostMapping("/updateStu")
    public Object updateStu(int id){
         stuService.updateStu(id);
         return "OK";
    }

    @PostMapping("/deleteStu")
    public Object deleteStu(int id){
        stuService.deleteStu(id);
        return "OK";
    }
}
