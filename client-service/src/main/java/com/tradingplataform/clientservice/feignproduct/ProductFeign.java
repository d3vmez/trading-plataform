package com.tradingplataform.clientservice.feignproduct;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;
import feign.Param;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductFeign {
	

	@RequestMapping(method = RequestMethod.GET, value = "/product")
	// @Headers("Authorization: {token}")
	// @Param("token")
	public List<Product> getAll(@RequestHeader("Authorization") String bearerToken);

}
