package com.saktaWdi.MyWebApp.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@TableName("notice")
@Data
public class Notice {
    @NotBlank(message = "公告ID不能为空")
    private Integer id;

    private String title = "公告";
    @NotBlank(message = "公告内容不能为空")
    private String content;
    private int status = 0;
}
