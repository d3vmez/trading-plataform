package com.tradingplataform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthClientsApplication.class, args);
	}

}
