package com.saktaWdi.MyWebApp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {
    private Integer num;
    private String pwd;
    private String name;
    @JsonProperty(required = false)
    private String avatarUrl;
}
