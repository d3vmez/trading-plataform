package com.tradingplataform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.service.impl.ExchangerInfoService;

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

}
