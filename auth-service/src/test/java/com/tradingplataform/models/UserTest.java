package com.tradingplataform.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tradingplataform.authservice.models.User;

class UserTest {
	
	User user;

	@BeforeEach
	void setUp() throws Exception {
		
		user = new User("marcos@marcos.com", "123");
		
	}

	@Test
	void testUser() {	
		User user = new User();
		assertEquals(null, user.getEmail());
	}

	@Test
	void testUserStringString() {

		User user = new User("pablo@pablo.com", "321");
		
		assertAll("Test user", 
				()->assertEquals("pablo@pablo.com", user.getEmail()),
				()->assertEquals("321", user.getPassword())
				);
	}
	
	@Test
	void testGetAndSet() {
		
		user.setEmail("pablo@pablo.com");
		user.setPassword("321");
		user.setId(2);
	
		assertAll("Test user", 
				()->assertEquals("pablo@pablo.com", user.getEmail()),
				()->assertEquals("321", user.getPassword()),
				()->assertEquals(2, user.getId())
				);
	}

}
