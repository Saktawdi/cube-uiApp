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

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accesToken=request.getHeader("token");
        if (accesToken==null){
            accesToken=request.getParameter("token");
        }
        if (StringUtils.isNotBlank(accesToken)){
            Claims claims= JWTUtil.checkJWT(accesToken);
            if (claims==null){
                //登录过期
                sendJsonMsg(response, CommonResult.fail("-2","登录已过期，请重新登录"));
                return false;
            }

            Integer userId=(Integer) claims.get("id");
            String userName=(String) claims.get("name");;
            Integer userVip=(Integer) claims.get("vip");
            String userNum=(String) claims.get("num");
            String userAvatarUrl=(String) claims.get("avatarUrl");
            String userCreateTime=(String) claims.get("createTime");

            request.setAttribute("user_id",userId);
            request.setAttribute("user_name",userName);
            request.setAttribute("user_num",userNum);
            request.setAttribute("user_vip",userVip);
            request.setAttribute("user_avatar_url",userAvatarUrl);
            request.setAttribute("user_create_time",userCreateTime);
            return true;
        }
        sendJsonMsg(response, CommonResult.fail("-1","登录身份出错"));
        return false;
    }


    private static void sendJsonMsg(HttpServletResponse response,Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writer().writeValueAsString(object));
            writer.close();
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
