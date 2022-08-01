package com.tradingplataform.productservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.productservice.model.Product;
import com.tradingplataform.productservice.service.impl.ExchangeInfoService;
import com.tradingplataform.productservice.service.impl.ProductService;

@RestController
@RequestMapping("/exchangeproduct")
@CrossOrigin
public class ExchangerInfoController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ExchangeInfoService exchangeInfoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
		
		Optional<Product> product = productService.find(id);
		
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product.get());
	}
	
	@GetMapping("/hascuantity/{idProduct}/{cuantity}")
	public ResponseEntity<Boolean> productHasCuantity(@PathVariable("idProduct") int idProduct, @PathVariable("cuantity") int cuantity){

		boolean hasCuantity = exchangeInfoService.productHasCuantity(idProduct, cuantity);
		
		return ResponseEntity.ok(hasCuantity);
	}
	
	@PostMapping("/updateCuantity/{idProducto}/{cuantity}")
	public ResponseEntity<Product> updateProduct(@PathVariable("idProducto") int idProduct, @PathVariable("cuantity") int cuantity){
		
		Product product = exchangeInfoService.updateProductCuantity(idProduct, cuantity);
		
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product);
		
	}
	
}
