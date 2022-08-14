package com.saktaWdi.MyWebApp.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("keyList")
@ToString
public class KeyList {
   @TableId("id")
   private Integer id;
   @TableField("secret_key")
   private String secretKey;
   @TableField("list_json")
   private String listJson;
}
