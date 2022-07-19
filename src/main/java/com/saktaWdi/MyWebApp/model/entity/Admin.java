package com.saktaWdi.MyWebApp.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    private Integer id;
    private int num;
    private int weight;
}
