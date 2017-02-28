package com.tanqed.sw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/* Application Runner class with the main method that starts SpringBootApplicaion */
@SpringBootApplication
public class WeChatServicesApplication extends SpringBootServletInitializer{

	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	 return application.sources(WeChatServicesApplication.class);
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(WeChatServicesApplication.class, args);
	}
}
