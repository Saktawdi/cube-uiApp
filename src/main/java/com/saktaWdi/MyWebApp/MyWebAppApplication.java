package com.saktaWdi.MyWebApp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.saktaWdi.MyWebApp.mapper")
public class MyWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWebAppApplication.class, args);
	}

}
