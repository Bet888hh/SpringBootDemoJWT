package com.Betto.ProgettoSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Betto.ProgettoSecurity.security.AppProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class ProgettoSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSecurityApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	@Bean(name = "AppProperties")
	public  AppProperties appPropertie() {
		return new  AppProperties();
	}

}
