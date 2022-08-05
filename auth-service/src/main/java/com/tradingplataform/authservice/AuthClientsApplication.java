package com.tradingplataform.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableEurekaClient
public class AuthClientsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AuthClientsApplication.class, args);
	}

}
