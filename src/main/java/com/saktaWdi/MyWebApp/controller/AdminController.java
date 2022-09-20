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
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("showAllUsers")
    public CommonResult showAllUsers(){
        List<Users> users=adminService.showAllUsers();
        if (!users.isEmpty()){
            return CommonResult.ok().setResult(users);
        }else{
            return CommonResult.fail();
        }
    }

    @GetMapping("showAllAdmin")
    public CommonResult showAllAdmin() {
        List<Admin> admins=adminService.showAllAdmin();
        if(!admins.isEmpty()){
            return CommonResult.ok().setResult(admins);
        }else{
            return CommonResult.fail();
        }
    }

    @PutMapping("addAdmin")
    public CommonResult addAdmin(@RequestBody int num,@RequestBody int weight){
        Admin admin = new Admin();
        admin.setNum(num);
        admin.setWeight(weight);
        int rows = adminService.addAdmin(admin);
        return rows == 1 ? CommonResult.ok(): CommonResult.fail();
    }

    @PostMapping("setUserState")
    public CommonResult setUserState(@RequestBody Integer num,@RequestBody Integer status){
        if(num == null) return CommonResult.fail().setResult("错误！没有传输用户账号");
        Users user = userService.serleUserByNum(num);
        if(user == null) return CommonResult.fail().setResult("操作失败，数据库中无此用户");
        if (status == 0 || status == 1){
            user.setState((status));
        }
        userService.updateUser(user);
        return CommonResult.ok();
    }

}
