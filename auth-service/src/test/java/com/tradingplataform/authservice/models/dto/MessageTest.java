package com.tradingplataform.authservice.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {MessageTest.class})
class MessageTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testMessage() {
		Message message = new Message();
		assertNotNull(message);
	}

	@Test
	@Order(2)
	void testMessageString() {
		Message message = new Message("Response");
		assertEquals("Response", message.getMessage());
		
	}
	
	@Test
	@Order(3)
	void testGetAndSet() {
		Message message = new Message();
		message.setMessage("Response");
		assertEquals("Response", message.getMessage());
	}

}
