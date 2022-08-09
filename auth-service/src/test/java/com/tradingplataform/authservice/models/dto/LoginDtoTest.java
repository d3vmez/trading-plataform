package com.tradingplataform.authservice.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {LoginDtoTest.class})
class LoginDtoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testLoginDto() {
		
		LoginDto dto = new LoginDto();
		assertNotNull(dto);
		
	}

	@Test
	@Order(2)
	void testLoginDtoStringString() {
		
		LoginDto dto = new LoginDto("marcos@marcos.com", "123");
		
		assertAll("LoginDtoTest", 
				()->assertEquals("marcos@marcos.com", dto.getEmail()),
				()->assertEquals("123", dto.getPassword())
				);
	}
	
	@Test
	void testGetAndSet() {
		
		LoginDto dto = new LoginDto();
		dto.setEmail("marcos@marcos.com");
		dto.setPassword("123");
	
		assertAll("Test user", 
				()->assertEquals("marcos@marcos.com", dto.getEmail()),
				()->assertEquals("123", dto.getPassword())
				);
	
	}
	
	

}
