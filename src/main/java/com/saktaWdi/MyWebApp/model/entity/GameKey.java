package com.saktaWdi.MyWebApp.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
@TableName("gameKey")
public class GameKey {
    @JsonIgnore
    private Integer id;
    private int userNum;
    private String games;
    @JsonIgnore
    private Date createTime;
}
