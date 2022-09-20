package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.UserMapper;
import com.saktaWdi.MyWebApp.model.entity.Users;
import com.saktaWdi.MyWebApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertUser(Users user) {
        int rows=0;
        try {
            rows=userMapper.insert(user);
        }catch (Exception e){
            log.info("【添加用户失败】，原因：可能是用户重复",e);
            return 0;
        }
        return rows;
    }

    @Override
    public Users serleUserByNum(int num) {
        Users user=userMapper.selectOne(new QueryWrapper<Users>().eq("num",num));
        return user;
    }

    @Override
    public int getMaxId() {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id");
        int usersNum=userMapper.selectCount(queryWrapper);
        return usersNum;
    }

    @Override
    public int updateUser(Users user) {
        return userMapper.updateById(user);
    }
}
