package com.saktaWdi.MyWebApp.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import com.saktaWdi.MyWebApp.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String adminToken = request.getHeader("token");
        if (adminToken==null){
            adminToken=request.getParameter("token");
        }
        if (StringUtils.isNotBlank(adminToken)) {
            Claims claims = JWTUtil.checkAdminJWT(adminToken);
            if (claims==null){
                //登录过期
                sendJsonMsg(response, CommonResult.fail("-2","登录已过期，请重新登录"));
                return false;
            }
            Integer weight=(Integer) claims.get("weight");
//            if(weight<10){
//                sendJsonMsg(response, CommonResult.fail("-2","登录失败,非法用户,权限不够"));
//                return false;
//            }
            request.setAttribute("admin_weight",weight);

            return true;

        }
        sendJsonMsg(response, CommonResult.fail("-1","登录身份出错"));
        return false;
    }

    private static void sendJsonMsg(HttpServletResponse response,Object object) {
        LoginInterceptor.responseSendJsonMsg(response, object);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
