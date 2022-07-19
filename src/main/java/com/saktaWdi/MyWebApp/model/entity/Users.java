package com.saktaWdi.MyWebApp.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ApiModel
@ToString
@TableName("users")
public class Users {
    private Integer id;
    private int num;
    private String pwd;
    private String name;
    private String avatarUrl;
    private int vip;
    private Date createTime;
}
