package com.tradingplataform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.models.User;
import com.tradingplataform.models.resttemplates.Product;
import com.tradingplataform.service.impl.UserService;

@RestController
@RequestMapping("/prueba")
@CrossOrigin
public class PruebaController {
	
	@Autowired
	private UserService userService;
	
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


}
