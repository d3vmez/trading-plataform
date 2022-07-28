package com.tradingplataform.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tradingplataform.authservice.models.dto.RegisterDto;

class RegisterDtoTest {

	RegisterDto user;

	@BeforeEach
	void setUp() throws Exception {
		
		user = new RegisterDto("marcos@marcos.com", "123");
	}

	@Test
	void testRegisterDto() {
		RegisterDto user = new RegisterDto();
		assertEquals(null, user.getEmail());
	}

	@Test
	void testRegisterDtoStringString() {
		RegisterDto user = new RegisterDto("pablo@pablo.com", "321");
		
		assertAll("Test user", 
				()->assertEquals("pablo@pablo.com", user.getEmail()),
				()->assertEquals("321", user.getPassword())
				);
	}

	@Test
	void testGetAndSet() {
		user.setEmail("pablo@pablo.com");
		user.setPassword("321");
	
		assertAll("Test user", 
				()->assertEquals("pablo@pablo.com", user.getEmail()),
				()->assertEquals("321", user.getPassword())
				);
	
	}

}
