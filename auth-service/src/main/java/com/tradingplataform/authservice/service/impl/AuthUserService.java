package com.tradingplataform.authservice.service.impl;


import java.math.BigDecimal;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.models.dto.LoginDto;
import com.tradingplataform.authservice.models.dto.RegisterDto;
import com.tradingplataform.authservice.models.dto.ResponseTokenDto;
import com.tradingplataform.authservice.security.JwtProvider;


@Service
@Transactional
public class AuthUserService{
	
	Logger log = Logger.getLogger(AuthUserService.class);
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * Método para registar un usuario en bd
	 * 
	 * @param RegisterDTO registerUserDto, objeto proviniente de la peticion del registro
	 * @return
	 */
	public User register(RegisterDto registerUserDto) {
		
		Optional<User> opUser = userService.findByEmail(registerUserDto.getEmail());
		if(opUser.isPresent()) {
			return null;
		}
		
		User user = new User(registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword()));
		user.setBalance(BigDecimal.ZERO);
		userService.save(user);
		
		return user;
		
	}
	
	public ResponseTokenDto login(LoginDto loginUserDto) {
		Optional<User> opUser = userService.findByEmail(loginUserDto.getEmail());
		if(opUser.isEmpty()) {
			return null;
		}
		
		// Comprobar si la contraseña encriptada coincide con la introducida en el login
		if(passwordEncoder.matches(loginUserDto.getPassword(), opUser.get().getPassword())) {
			
			// Generar token
			String token = jwtProvider.createToken(loginUserDto.getEmail());
			ResponseTokenDto responseTokenDto = new ResponseTokenDto(token,loginUserDto.getEmail());
			return responseTokenDto;
			
		}
		
		return null;
				
	}
	
	public ResponseTokenDto validateToken(String token) {
		if(!jwtProvider.isValidateToken(token)) {
			return null;
		}
		
		String userName = jwtProvider.getEmailFromToken(token);
		
		if(userService.findByEmail(userName).isEmpty()) {
			return null;
		}
		
		return new ResponseTokenDto(token, userName);
		
	}






}
