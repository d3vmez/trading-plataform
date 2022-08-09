package com.tradingplataform.notificationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { MailSenderServiceTest.class })
class MailSenderServiceTest {

	@Mock
	JavaMailSender javaMailSender;
	
	@InjectMocks
	MailSenderService mailSenderService;
	
	@Test @Order(1)
	void testSendEmail() {

		assertEquals(true, mailSenderService.sendEmail("recipient", "subject", "content"));

	}

}
