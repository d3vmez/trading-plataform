package com.tradingplataform.authservice.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {RegisterDtoTest.class})
class RegisterDtoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testRegisterDto() {
		
		RegisterDto dto = new RegisterDto();
		assertNotNull(dto);
	
	}

	@Test
	@Order(2)
	void testRegisterDtoStringString() {
		
		RegisterDto dto = new RegisterDto("marcos@marcos.com", "123");
		
		assertAll("RegisterDtotest", 
				()->assertEquals("marcos@marcos.com", dto.getEmail()),
				()->assertEquals("123", dto.getPassword())
				);
	
	}
	
	@Test
	@Order(3)
	void testGetAndSet() {
		
		RegisterDto dto = new RegisterDto();
		dto.setEmail("pablo@pablo.com");
		dto.setPassword("321");
	
		assertAll("RegisterDtotest", 
				()->assertEquals("pablo@pablo.com", dto.getEmail()),
				()->assertEquals("321", dto.getPassword())
				);
	
	}

}
