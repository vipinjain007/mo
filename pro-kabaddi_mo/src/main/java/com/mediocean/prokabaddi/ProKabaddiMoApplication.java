package com.mediocean.prokabaddi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProKabaddiMoApplication extends SpringBootServletInitializer {
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProKabaddiMoApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProKabaddiMoApplication.class, args);
	}
}
