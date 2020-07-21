package com.imooc3.controller;

import com.imooc3.pojo.io.UserBO;
import com.imooc3.utils.IMOOCJSONResult;
import com.imooc3.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "注册登录",tags = "用于注册登录的相关接口")
@RestController
@RequestMapping("passport")   /*写上路由*/
public class PassPortController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){/*前面加前缀，是代表它是一种请求类型的参数，而不是路径参数*/
        //判断用户名不能为空
        if(StringUtils.isBlank(username)) {/*使用apache lang3下的Utils;isnotBlank可以额外判断是不是空字符串，isBlank判断他是不是为空*/
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }

        //2.查找注册的用户名是否存在
        boolean isExist=userService.queryUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }

        //3.请求成功，用户名没有重复;200代表请求成功。
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO){/*由于前段返回的是一个json对象，所以这里加上@RequestBody*/
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 1. 查询用户名是否存在
        boolean isExist=userService.queryUsernameIsExist(username);
        if(isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }

        // 2. 判断长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6");
        }

        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        // 4. 实现注册
        userService.createUser(userBO);

        return IMOOCJSONResult.ok();
    }

}
