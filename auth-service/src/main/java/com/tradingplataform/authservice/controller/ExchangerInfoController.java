package com.tradingplataform.authservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.authservice.service.impl.ExchangerInfoService;

@RestController
@RequestMapping("/exchange")
@CrossOrigin
public class ExchangerInfoController {
	
	@Autowired
	private ExchangerInfoService exchangerInfoService;
	
	@GetMapping("{token}")
	public ResponseEntity<Integer> getId(@PathVariable("token") String token){
		
		Integer id = exchangerInfoService.getIdFromToken(token);
		System.out.println(id);
		
		if(id == null) {
			return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
		}
		
		return ResponseEntity.ok(id);
		
	}
	
	@GetMapping("/hasbalance/{balance}")
	public ResponseEntity<Boolean> hasBalance(@PathVariable("balance") BigDecimal balance){
		
		if(balance==null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(null);
	}

}
