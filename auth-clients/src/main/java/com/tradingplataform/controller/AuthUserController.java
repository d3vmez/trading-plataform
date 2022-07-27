package com.tradingplataform.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.models.User;
import com.tradingplataform.models.dto.LoginDto;
import com.tradingplataform.models.dto.Message;
import com.tradingplataform.models.dto.RegisterDto;
import com.tradingplataform.models.dto.ResponseTokenDto;
import com.tradingplataform.security.JwtProvider;
import com.tradingplataform.service.impl.AuthUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
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
			return new ResponseEntity(new Message("Wrong fields!!"), HttpStatus.BAD_REQUEST);
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
