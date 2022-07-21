package com.tradingplataform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.models.dto.Message;

@RestController
@RequestMapping("/prueba")
@CrossOrigin
public class PruebaController {
	
	@GetMapping("/lo")
	public ResponseEntity<?> prueba(){
		return new ResponseEntity(new Message("prueba"), HttpStatus.I_AM_A_TEAPOT);
	}


}
