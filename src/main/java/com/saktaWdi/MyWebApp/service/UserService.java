package com.saktaWdi.MyWebApp.service;

import com.saktaWdi.MyWebApp.model.entity.Users;

public interface UserService {
    int insertUser(Users user);
    Users serleUserByNum(int num);
    int getMaxId();
}