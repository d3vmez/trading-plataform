package com.tradingplataform.authservice.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.security.JwtProvider;

@Service
public class ExchangerInfoService {
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;
	
	public Integer getIdFromToken(String token) {
		
		// Obtener token sin Bearer
		String formatedToken = this.getToken(token);
		
		// Comprobar si el token es válido
		if(!jwtProvider.isValidateToken(formatedToken)) {
			return null;
		}
		
		// Obtener el email de las claims del token
		String email = jwtProvider.getEmailFromToken(formatedToken);
		
		// Obtener el id del usuario que contiene el token
		Optional<User> user = userService.findByEmail(email);
		
		if(user.isEmpty()) {
			return null;
		}
		
		int id = user.get().getId();
		
		return id;
			
	}
	
	
	private String getToken(String token) {
		
		if(token == null) {
			return null;
		}
		
		if(token.startsWith("Bearer")) {
			return token.replace("Bearer", "");
		}
		
		return token;
		
	}

}
