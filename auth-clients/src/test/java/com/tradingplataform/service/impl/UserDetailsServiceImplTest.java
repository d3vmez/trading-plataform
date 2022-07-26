package com.tradingplataform.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import com.tradingplataform.models.User;


class UserDetailsServiceImplTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLoadUserByUsername() {
		
		final String email1 = "marcos@marcos.com";
		final String email2 = "marcos2@marcos.com";
		final Optional<User> user1 = Optional.of(new User(email1,"clave"));
		
		when(userService.findByEmail(email1)).thenReturn(user1);
		
		final UserDetails userDetails1 = userDetailsServiceImpl.loadUserByUsername(email1);
		
		assertThrows(UsernameNotFoundException.class,()-> {userDetailsServiceImpl.loadUserByUsername(email2);});
		assertNotNull(userDetails1);
			
	}

}
