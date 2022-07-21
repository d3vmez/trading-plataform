package com.tradingplataform.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageTest {
	
	Message message;

	@BeforeEach
	void setUp() throws Exception {
		message = new Message();
	}

	@Test
	void testMessage() {
		Message message = new Message();
		assertEquals(null, message.getMessage());	
	}

	@Test
	void testMessageString() {
		Message message = new Message("Response");
		assertEquals("Response", message.getMessage());
	}

	@Test
	void testGetAndSet() {
		message.setMessage("Response");
		assertEquals("Response", message.getMessage());
	}

}
