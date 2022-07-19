package com.saktaWdi.MyWebApp.controller;

import com.saktaWdi.MyWebApp.model.entity.Users;
import com.saktaWdi.MyWebApp.model.request.LoginRequest;
import com.saktaWdi.MyWebApp.model.request.RegisterRequest;
import com.saktaWdi.MyWebApp.service.UserService;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import com.saktaWdi.MyWebApp.utils.CommonUtils;
import com.saktaWdi.MyWebApp.utils.FilesUtils;
import com.saktaWdi.MyWebApp.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1/pri/user")
@Api(value = "用户模块")
@Slf4j
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    @ApiOperation(value = "注册用户")
    public CommonResult<String> insertUser(@RequestBody RegisterRequest registerRequest) throws Exception{
        Users user=new Users();
        user.setId(userService.getMaxId()+1);
        user.setName(registerRequest.getName());
        user.setNum(registerRequest.getNum());
        user.setPwd(CommonUtils.MD5(registerRequest.getPwd()));
        user.setVip(0);
        if(registerRequest.getAvatarUrl().isEmpty()){
            user.setAvatarUrl("/assets/logo.png");
        }else{
            user.setAvatarUrl(registerRequest.getAvatarUrl());
        }
        user.setCreateTime(new Date());
        int rows=userService.insertUser(user);
        return rows == 1 ? CommonResult.ok() : CommonResult.fail();
    }

    @PostMapping("login")
    public CommonResult login(@RequestBody LoginRequest loginRequest){
        Users user=new Users();
        user=userService.serleUserByNum(loginRequest.getNum());
        String pwd=loginRequest.getPwd();
        if(user==null){
            return CommonResult.fail("-2","没有找到此用户");
        }else if(user.getPwd().equals(CommonUtils.MD5(pwd))){
            String token= JWTUtil.getJsonToken(user);
            return CommonResult.ok("2","登录成功").setResult(token);
        }else{
            return  CommonResult.fail("-21","账号或密码错误");
        }
    }

}
