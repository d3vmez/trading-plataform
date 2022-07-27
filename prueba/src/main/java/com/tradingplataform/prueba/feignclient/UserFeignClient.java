package com.tradingplataform.prueba.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "auth-clients")
public interface UserFeignClient {
	
	// Devolver id del usuario a partir de su token
	@RequestMapping(method = RequestMethod.GET, value = "/exchange/{token}")
	Integer getId(@PathVariable("token") String token);

}
