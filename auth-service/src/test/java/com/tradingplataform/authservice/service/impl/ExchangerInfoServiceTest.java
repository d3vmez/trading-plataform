package com.tradingplataform.authservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ExchangerInfoService.class})
class ExchangerInfoServiceTest {
	
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testGetIdFromToken() {
		fail("Not yet implemented");
	}

	@Test
	@Order(2)
	void testUserHasBalance() {
		fail("Not yet implemented");
	}

}
