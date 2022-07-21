package com.tradingplataform.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tradingplataform.models.resttemplates_feign.Product;


@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductFeignClient {
	
	// Crear producto
	 @RequestMapping(method = RequestMethod.POST, value = "/product")
    Product save(@RequestBody Product product);

}
