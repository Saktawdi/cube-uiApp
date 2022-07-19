package com.saktaWdi.MyWebApp.utils;

import lombok.Data;


import java.io.Serializable;
@Data
public abstract class BaseResult<T> implements Serializable {
    private boolean success=false;
    private String message;
    private T data;
    private String  code;
}
