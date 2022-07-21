package com.tradingplataform.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class LoginDtoTest {
	
	LoginDto user;

	@BeforeEach
	void setUp() throws Exception {
		
		user = new LoginDto("marcos@marcos.com", "123");
	}

	@Test
	void testLoginDto() {
		LoginDto user = new LoginDto();
		assertEquals(null, user.getEmail());
	}

	@Test
	void testLoginDtoStringString() {
		LoginDto user = new LoginDto("pablo@pablo.com", "321");
		
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
