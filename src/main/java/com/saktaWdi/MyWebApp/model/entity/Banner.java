package com.saktaWdi.MyWebApp.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("banner")
public class Banner {
    private Integer id;
    private String imgUrl;
    private String toUrl;
    private int weight;
}
