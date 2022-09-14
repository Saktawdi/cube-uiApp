package com.saktaWdi.MyWebApp.service;

import com.saktaWdi.MyWebApp.model.entity.Admin;
import com.saktaWdi.MyWebApp.model.entity.Users;

import java.util.List;

public interface AdminService {
    Admin selectByNum(int num);
    List<Users> showAllUsers();
    List<Admin> showAllAdmin();
    int addAdmin(Admin admin);
}

