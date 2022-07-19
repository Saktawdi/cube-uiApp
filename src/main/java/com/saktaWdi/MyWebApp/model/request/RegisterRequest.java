package com.saktaWdi.MyWebApp.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private Integer num;
    private String pwd;
    private String name;
    private String avatarUrl;
}
