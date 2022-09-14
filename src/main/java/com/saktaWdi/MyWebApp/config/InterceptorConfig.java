package com.saktaWdi.MyWebApp.config;

import com.saktaWdi.MyWebApp.interceptor.AdminInterceptor;
import com.saktaWdi.MyWebApp.interceptor.CorsinInterceptor;
import com.saktaWdi.MyWebApp.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration
@Slf4j
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
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register",
                        "/swagger**/**",
                        "/api/v1/pri/admin/*",
                        "/api/v1/pri/gameKey/updataKeysApi","/api/v1/pri/gameKey/getKeys");

        //管理员登录token拦截器配置
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/api/v1/pri/admin/*/**")
                .excludePathPatterns("/api/v1/pri/admin/login");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String gitPath = path.getParentFile().getParentFile().getParent() + File.separator + "logistics";
        log.info("[addResourceHandlers_test]:"+gitPath);
        registry.addResourceHandler("/userFiles/**")
                .addResourceLocations(gitPath
                        +System.getProperty("file.separator")
                        +"userFiles"
                        +System.getProperty("file.separator"));

        registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
