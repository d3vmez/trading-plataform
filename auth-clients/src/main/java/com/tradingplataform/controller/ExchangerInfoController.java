package com.tradingplataform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.service.impl.ExchangerInfoService;

@RestController
@RequestMapping("/exchange")
public class ExchangerInfoController {
	
	@Autowired
	private ExchangerInfoService exchangerInfoService;
	
	@GetMapping
	public ResponseEntity<Integer> getId(@PathVariable("token") String token){
		
		Integer id = exchangerInfoService.getIdFromToken(token);
		
		if(id == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(id);
		
	}

}
