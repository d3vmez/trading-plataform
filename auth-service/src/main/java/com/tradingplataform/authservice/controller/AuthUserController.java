package com.tradingplataform.authservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.models.dto.LoginDto;
import com.tradingplataform.authservice.models.dto.Message;
import com.tradingplataform.authservice.models.dto.RegisterDto;
import com.tradingplataform.authservice.models.dto.ResponseTokenDto;
import com.tradingplataform.authservice.security.JwtProvider;
import com.tradingplataform.authservice.service.impl.AuthUserService;
import com.tradingplataform.authservice.service.impl.ExchangerInfoService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8090")
public class AuthUserController {
	
	@Autowired
	private AuthUserService authUserService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginUserDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Wrong fields!!"), HttpStatus.BAD_REQUEST);
		}
		
		ResponseTokenDto responseTokenDto = authUserService.login(loginUserDto);
		if(responseTokenDto == null) {
			return new ResponseEntity(new Message("Invalid password or email!"), HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity(responseTokenDto, HttpStatus.OK);
			
	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<?> login(@Valid @RequestParam String token){
		
		ResponseTokenDto responseTokenDto = authUserService.validateToken(token);
		if(responseTokenDto == null) {
			return new ResponseEntity(new Message("Wrong fields!!"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(responseTokenDto, HttpStatus.OK);
			
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerUserDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Wrong fields!!"), HttpStatus.BAD_REQUEST);
		}
		
		User user = authUserService.register(registerUserDto);
		
		if(user == null) {
			return new ResponseEntity(new Message("El usuario ya existe"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(user, HttpStatus.CREATED);
		
	}
		
}
