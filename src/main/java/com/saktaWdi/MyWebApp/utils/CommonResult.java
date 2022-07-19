package com.saktaWdi.MyWebApp.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
public class CommonResult<T> extends BaseResult implements Serializable {
    private static final   String SUCCESSCODE="0";

    private static final   String ERRORCODE="-1";




    public static <T> CommonResult<T> ok(String code, String message) {
        return baseCreate(code, message, true);
    }
    public static CommonResult fail(String code, String msg) {
        return baseCreate(code, msg, false);
    }
    public static CommonResult fail() {
        return fail(ERRORCODE,"操作失败");
    }
    public static CommonResult ok() {
        return ok(SUCCESSCODE,"操作成功");
    }

    private static <T> CommonResult<T> baseCreate(String code, String msg, boolean success) {
        CommonResult result = new CommonResult();
        result.setCode(code);
        result.setSuccess(success);
        result.setMessage(msg);
        return result;
    }
    public CommonResult<T> setResult(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return (T) super.getData();
    }
}
