package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.AdminMapper;
import com.saktaWdi.MyWebApp.model.entity.Admin;
import com.saktaWdi.MyWebApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectByNum(int num) {
        Admin admin=adminMapper.selectOne(new QueryWrapper<Admin>().eq("num",num));
        return admin;
    }
}
