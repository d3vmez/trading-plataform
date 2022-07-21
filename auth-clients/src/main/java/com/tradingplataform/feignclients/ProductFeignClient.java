package com.tradingplataform.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tradingplataform.models.resttemplates_feign.Product;


@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductFeignClient {
	
	// Crear producto
	@RequestMapping(method = RequestMethod.POST, value = "/product")
	Product save(@RequestBody Product product);
	
	// Mostrar productos
	@RequestMapping(method = RequestMethod.GET, value = "/product/byuser/{userId}")
	List<Product> getProducts(@PathVariable("userId") int userId);
	
}
