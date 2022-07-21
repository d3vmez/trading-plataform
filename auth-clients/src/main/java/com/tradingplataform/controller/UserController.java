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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.models.User;
import com.tradingplataform.models.dto.LoginDto;
import com.tradingplataform.models.dto.Message;
import com.tradingplataform.models.dto.RegisterDto;
import com.tradingplataform.models.dto.ResponseDto;
import com.tradingplataform.security.JwtProvider;
import com.tradingplataform.service.impl.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerUser, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Wrong fields!!"), HttpStatus.BAD_REQUEST);
		}
		
		if(userService.findByEmail(registerUser.getEmail()).isPresent()) {
			return new ResponseEntity(new Message("Emails exists!!"), HttpStatus.BAD_REQUEST);
		}
		
		User user = new User(registerUser.getEmail(), passwordEncoder.encode(registerUser.getPassword()));
		
		// TODO asignar roles
		
		userService.save(user);
		return new ResponseEntity(new Message("User create!!"), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginUser, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Wrong fields!!"), HttpStatus.BAD_REQUEST);
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.createToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		ResponseDto responseDto = new ResponseDto(token, userDetails.getUsername());
		return new ResponseEntity(responseDto, HttpStatus.OK);
	}
	
	
	
	
}
