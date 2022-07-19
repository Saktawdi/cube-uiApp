package com.saktaWdi.MyWebApp.controller;

import com.saktaWdi.MyWebApp.model.entity.Admin;
import com.saktaWdi.MyWebApp.model.entity.Users;
import com.saktaWdi.MyWebApp.model.request.LoginRequest;
import com.saktaWdi.MyWebApp.service.AdminService;
import com.saktaWdi.MyWebApp.service.UserService;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import com.saktaWdi.MyWebApp.utils.CommonUtils;
import com.saktaWdi.MyWebApp.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/pri/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public CommonResult loginAdmin(@RequestBody LoginRequest loginRequest){
        Admin admin=adminService.selectByNum(loginRequest.getNum());
        if (admin!=null){
            Users user=userService.serleUserByNum(admin.getNum());
            String pwd=loginRequest.getPwd();
            if (user.getPwd().equals(CommonUtils.MD5(pwd))){
                String adminToken=JWTUtil.getAdminToken(admin);
                return CommonResult.ok("2","管理员登录成功").setResult(adminToken);
            }else{
                return CommonResult.fail("-2","账号或密码错误");
            }
        }else{
            return CommonResult.fail("-1","未查到此管理员信息");
        }
    }
}
