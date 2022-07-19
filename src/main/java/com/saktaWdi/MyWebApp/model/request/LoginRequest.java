package com.saktaWdi.MyWebApp.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private int num;
    private String pwd;
}
