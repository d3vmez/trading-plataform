package com.tradingplataform.authservice.models.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ResponseTokenDto.class})
class ResponseTokenDtoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testResponseTokenDto() {
		
		ResponseTokenDto responseDto = new ResponseTokenDto();
		assertNotNull(responseDto);
		assertEquals("Bearer", responseDto.getBearer());
	}

	@Test
	@Order(2)
	void testResponseTokenDtoStringString() {
		ResponseTokenDto responseDto = new ResponseTokenDto("token", "userName");

		assertAll("ResponseDto()", () -> assertEquals("token", responseDto.getToken()),
				() -> assertEquals("Bearer", responseDto.getBearer()),
				() -> assertEquals("userName", responseDto.getUserName()));
	}
	
	@Test
	@Order(3)
	void testGetToken() {
		
		ResponseTokenDto responseDto = new ResponseTokenDto();
		
		responseDto.setToken("token");
		responseDto.setUserName("username");
		responseDto.setBearer("Bearer");

		assertAll("ResponseDto()", () -> assertEquals("token", responseDto.getToken()),
				() -> assertEquals("Bearer", responseDto.getBearer()),
				() -> assertEquals("username", responseDto.getUserName()));
	}

}
