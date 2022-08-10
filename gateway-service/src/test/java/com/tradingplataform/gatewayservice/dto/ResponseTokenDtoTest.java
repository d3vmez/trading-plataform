package com.tradingplataform.gatewayservice.dto;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ResponseTokenDtoTest.class })
class ResponseTokenDtoTest {

	@Test @Order(1)
	void testResponseTokenDtoModel() {

		ResponseTokenDto dto1 = new ResponseTokenDto("token", "userName");
		ResponseTokenDto dto2 = new ResponseTokenDto();
		dto2.setToken("token");
		dto2.setUserName("userName");
		dto2.setBearer("bearer");
		
		assertAll("Test getters and setters",
				()-> assertEquals(dto1.getToken(), dto2.getToken()),
				()-> assertEquals(dto1.getUserName(), dto2.getUserName()),
				()-> assertEquals("bearer",dto2.getBearer())
				);
		
	}

}
