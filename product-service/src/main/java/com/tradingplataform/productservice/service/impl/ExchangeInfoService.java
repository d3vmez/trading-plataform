package com.tradingplataform.productservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.productservice.model.Product;

@Service
public class ExchangeInfoService {

	@Autowired
	ProductService productService;
	
	public boolean productHasCuantity(int id, int cuantity) {
		
		Optional<Product> productOp = productService.find(id);
		
		if(productOp.isEmpty()) {
			return false;
		}
		
		Product product = productService.find(id).get();
		
		if(cuantity>product.getCuantity()) {
			return false;
		}
		
		return true;
	}
	
	public Product updateProductCuantity(int id, int cuantity) {
		
		Optional<Product> productOp = productService.find(id);
		
		if(productOp.isEmpty()) {
			return null;
		}
		
		Product product = productService.find(id).get();
		
		int newCuantity = product.getCuantity()-cuantity;
		
		product.setCuantity(newCuantity);
		productService.update(product);
		
		return product;
	}
}
