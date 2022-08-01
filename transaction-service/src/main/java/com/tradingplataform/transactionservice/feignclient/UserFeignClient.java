package com.tradingplataform.transactionservice.feignclient;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface UserFeignClient {

	@RequestMapping(method = RequestMethod.GET,value = "/exchange/hasbalance/{balance}/{token}")
	public Boolean hasBalance(@PathVariable("balance") BigDecimal balance, @PathVariable("token") String token);
	
	@RequestMapping(method = RequestMethod.GET ,value ="/exchange/user/{userId}")
	public User getUser(@PathVariable("userId") int userId);
	
	@RequestMapping(method = RequestMethod.POST ,value ="exchange/updateUser/{userId}/{newBalance}")
	User updateUserBalance(@PathVariable("newBalance") BigDecimal newBalance, @PathVariable("userId") int userId);
	
	// Devolver id del usuario a partir de su token
	@RequestMapping(method = RequestMethod.GET, value = "/exchange/{token}")
	Integer getId(@PathVariable("token") String token);
}