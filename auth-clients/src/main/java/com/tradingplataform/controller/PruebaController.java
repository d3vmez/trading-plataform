package com.tradingplataform.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.models.User;
import com.tradingplataform.models.resttemplates_feign.Product;
import com.tradingplataform.service.impl.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/prueba")
@CrossOrigin
public class PruebaController {
	
	@Autowired
	private UserService userService;
	
	// Usando RestTemplate
	@CircuitBreaker(name = "productCB", fallbackMethod = "fallbackgetProducts")
	@GetMapping("/product/{userId}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable("userId") int userId){
		Optional<User> user = userService.find(userId);
		if(user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			
			List<Product> products = userService.findProducts(userId);
			return ResponseEntity.ok(products);
		}
	}
	
	public ResponseEntity<?> fallbackgetProducts(@PathVariable("userId") int userId, RuntimeException e){
		return new ResponseEntity<>("Usuario con id: " + userId + " error al buscar sus productos", HttpStatus.OK);
	}
	/////////////////////////////////////////////////////////////////
	
	// Usando Feign
	@CircuitBreaker(name = "productCB", fallbackMethod = "fallbackcreateProduct")
	@PostMapping("/createproduct/{userId}")
	public ResponseEntity<Product> createProduct(@PathVariable("userId") int userId, @RequestBody Product product){
		
		if(userService.find(userId).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
			
		Product productNew = userService.saveProduct(product, userId);
		return ResponseEntity.ok(productNew);
	
	}
	
	public ResponseEntity<?> fallbackcreateProduct(@PathVariable("userId") int userId, @RequestBody Product product, RuntimeException e){
		return new ResponseEntity<>("Usuario con id: " + userId + " error al crear su producto", HttpStatus.OK);
	}
	
	// Usando Feign
	@CircuitBreaker(name = "productCB", fallbackMethod = "fallbackgetProductsWithUser")
	@GetMapping("/getProductsWithUser/{userId}")
	public ResponseEntity<Map<String, Object>> getProductsWithUser(@PathVariable("userId") int userId){
		
		Map<String, Object> mapProducts = userService.getProductsWithUser(userId);
		System.out.println(mapProducts.toString());
		return ResponseEntity.ok(mapProducts);
	
	}
	
	public ResponseEntity<?> fallbackgetProductsWithUser(@PathVariable("userId") int userId, RuntimeException e){
		return new ResponseEntity<>("Usuario con id: " + userId + " error al crear su producto", HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////////////////
}
