package com.tradingplataform.transactionservice.feignclient;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "transaction-service")
public interface UserFeignClient {

	@RequestMapping(method = RequestMethod.GET,value = "/exchange/hasbalance/{balance}/{token}")
	public Boolean hasBalance(@PathVariable("balance") BigDecimal balance, @PathVariable("token") String token);
	
	@RequestMapping(method = RequestMethod.POST ,value ="/exchange/user/{userId}")
	public User getUser(@PathVariable("id") int id);
	
	@RequestMapping(method = RequestMethod.POST ,value ="exhange//updateUser/{userId}/{newBalance}")
	User updateUserBalance(@PathVariable("newBlance") BigDecimal newBalance, @PathVariable("userId") int userId);
}