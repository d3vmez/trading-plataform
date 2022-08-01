package com.tradingplataform.transactionservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-service")
public interface ProductFeign {

	@RequestMapping(method = RequestMethod.GET,value = "/exchangeproduct/{id}")
	public Product getProduct(@PathVariable("id") int id);
	
	@RequestMapping(method = RequestMethod.GET,value = "/exchangeproduct/hascuantity/{idProduct}/{cuantity}")
	public boolean productHasCuantity(@PathVariable("idProduct") int idProduct, @PathVariable("cuantity") int cuantity);
	
	@RequestMapping(method = RequestMethod.POST,value = "/exchangeproduct/updateCuantity/{idProducto}/{cuantity}")
	public Product updateProduct(@PathVariable("idProducto") int idProduct, @PathVariable("cuantity") int cuantity);
}
