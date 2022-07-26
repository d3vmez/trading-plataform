package com.tradingplataform.gatewayservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	/**
	 * 
	 * Bean para permitir una comunicación síncrona entre el filtro
	 * y el endpoint de los microservicios
	 * 
	 * @return
	 */
	@Bean
	@LoadBalanced
	public WebClient.Builder builder(){
		return WebClient.builder();
	}

}
