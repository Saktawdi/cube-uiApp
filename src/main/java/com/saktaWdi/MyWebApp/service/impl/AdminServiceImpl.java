package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.AdminMapper;
import com.saktaWdi.MyWebApp.mapper.UserMapper;
import com.saktaWdi.MyWebApp.model.entity.Admin;
import com.saktaWdi.MyWebApp.model.entity.Users;
import com.saktaWdi.MyWebApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Admin selectByNum(int num) {
        Admin admin=adminMapper.selectOne(new QueryWrapper<Admin>().eq("num",num));
        return admin;
    }

    @Override
    public List<Users> showAllUsers() {
        List<Users> users= userMapper.selectList(null);
        return users;
    }

    @Override
    public List<Admin> showAllAdmin() {
        List<Admin> admin = adminMapper.selectList(null);
        return admin;
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.insert(admin);
    }
}
