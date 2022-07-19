package com.saktaWdi.MyWebApp;


import com.saktaWdi.MyWebApp.mapper.UserMapper;
import com.saktaWdi.MyWebApp.model.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyWebAppApplication.class)
@Slf4j
public class UserLoginTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public int serleUserByNum() {
        Users user=new Users();
        int num=1008611;
        user=userMapper.selectById(num);
        if(user.toString()!=null){
            return 1;
        }else{
            return 0;
        }
    }
}
