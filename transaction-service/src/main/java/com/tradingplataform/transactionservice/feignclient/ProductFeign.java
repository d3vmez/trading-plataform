package com.tradingplataform.transactionservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-service")
public interface ProductFeign {

	@RequestMapping(method = RequestMethod.GET,value = "/exchangeproduct/{id}")
	public Product getProduct(@PathVariable("id") int id);
}
