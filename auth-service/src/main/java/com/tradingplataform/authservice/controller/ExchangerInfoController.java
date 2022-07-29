package com.tradingplataform.authservice.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.service.impl.ExchangerInfoService;
import com.tradingplataform.authservice.service.impl.UserService;

@RestController
@RequestMapping("/exchange")
@CrossOrigin
public class ExchangerInfoController {
	
	Logger log = Logger.getLogger(ExchangerInfoController.class);
	
	@Autowired
	private ExchangerInfoService exchangerInfoService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{token}")
	public ResponseEntity<Integer> getId(@PathVariable("token") String token){
		
		Integer id = exchangerInfoService.getIdFromToken(token);
		System.out.println(id);
		
		if(id == null) {
			log.error("invalid token");
			return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
		}
		
		return ResponseEntity.ok(id);
		
	}
	
	@GetMapping("/hasbalance/{balance}/{token}")
	public ResponseEntity<Boolean> hasBalance(@PathVariable("balance") BigDecimal balance, @PathVariable("token") String token){
		
		if(balance==null) {
			return ResponseEntity.noContent().build();
		}
		
		if(exchangerInfoService.userHasBalance(balance, token)) {
			return ResponseEntity.ok(true);
		}
		
		return ResponseEntity.ok(false);
	}
	
	@PostMapping("/updateUser/{userId}/{newBalance}")
	public ResponseEntity<User> updateUserBalance(@PathVariable("newBalance") BigDecimal newBalance, @PathVariable("userId") int userId){
		
		Optional<User> user = userService.find(userId);
		
		// Comprobar que el usuario existe
		if(user.isEmpty()) {
			log.error("Invalid user with id: " + userId + "in updateUserBalance");
			return ResponseEntity.notFound().build();
		}
		
		// Comprobar que el balance sea válido
		if(newBalance == null) {
			log.error("Invalid balance in updateUserBalance");
			return ResponseEntity.noContent().build();
		}
		
		User tempUser = user.get();
		
		tempUser.setBalance(newBalance);
		userService.save(tempUser);
		log.info("Updated balance of user with: " + userId);
		
		return ResponseEntity.ok(tempUser);
	}
	
	@GetMapping("/user/{userID}")
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId){
		Optional<User> user = userService.find(userId);
		
		// Comprobar que el usuario existe
		if(user.isEmpty()) {
			log.error("Invalid user with id: " + userId + "in updateUserBalance");
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user.get());
		
	}

}
