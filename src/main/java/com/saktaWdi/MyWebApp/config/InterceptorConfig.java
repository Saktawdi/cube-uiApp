package com.saktaWdi.MyWebApp.config;

import com.saktaWdi.MyWebApp.interceptor.AdminInterceptor;
import com.saktaWdi.MyWebApp.interceptor.CorsinInterceptor;
import com.saktaWdi.MyWebApp.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {
    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Bean
    CorsinInterceptor corsinInterceptor(){
        return new CorsinInterceptor();
    }

    @Bean
    AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //跨流域配置
        registry.addInterceptor(corsinInterceptor()).addPathPatterns("/**");

        //登录token拦截器配置
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register","/swagger**/**","/api/v1/pri/admin/*");

        //管理员登录token拦截器配置
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/api/v1/pri/admin/*/**")
                .excludePathPatterns("/api/v1/pri/admin/login");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
