package com.tradingplataform.authservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tradingplataform.authservice.models.SecurityUser;
import com.tradingplataform.authservice.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserService userService; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Obtener al usuario de la BD por email
		Optional<User> opUser = userService.findByEmail(email);

		if (opUser.isPresent()) {

			User user = opUser.get();
			return SecurityUser.build(user);

		} else {

			throw new UsernameNotFoundException("Usuario con email: " + email + " no encontrado");

		}

	}

}
