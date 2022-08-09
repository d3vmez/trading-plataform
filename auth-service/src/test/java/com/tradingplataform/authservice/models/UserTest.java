package com.tradingplataform.authservice.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {UserTest.class})
class UserTest {
	
	User user;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testUser() {
		User user = new User();
		assertNotNull(user);
	}

	@Test
	@Order(2)
	void testUserStringString() {
		
		User user = new User("marcos@marcos.com", "123");
		assertAll("User", 
				()->assertEquals("marcos@marcos.com", user.getEmail()),
				()->assertEquals("123", user.getPassword())
				);
		
	}
	
	@Test
	@Order(3)
	void testUserStringStringBigDecimal() {
		
		User user = new User("marcos@marcos.com", "123", new BigDecimal(1000));
		assertAll("User", 
				()->assertEquals("marcos@marcos.com", user.getEmail()),
				()->assertEquals("123", user.getPassword()),
				()->assertEquals(new BigDecimal(1000), user.getBalance())
				);
	}
	
	@Test
	@Order(4)
	void testUserGetAndSet() {
		
		User user = new User();
		user.setEmail("marcos@marcos.com");
		user.setPassword("123");
		user.setBalance(new BigDecimal(1000));
		assertAll("User", 
				()->assertEquals("marcos@marcos.com", user.getEmail()),
				()->assertEquals("123", user.getPassword()),
				()->assertEquals(new BigDecimal(1000), user.getBalance())
				);
		
	}

}
