package com.tradingplataform.notificationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tradingplataform.notificationservice.model.dto.NotificationDTO;
import com.tradingplataform.notificationservice.service.MailSenderService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { MailSenderControllerTest.class })
class MailSenderControllerTest {

	@Mock
	MailSenderService mailSenderService;
	
	@InjectMocks
	MailSenderController mailSenderController;
	
	@Test @Order(1)
	void testSendEmail() {

		NotificationDTO notificationDto = new NotificationDTO("recipient", "subject", "content");
		
		when(mailSenderService.sendEmail("recipient", "subject", "content")).thenReturn(true);
		ResponseEntity<Boolean> res = mailSenderController.sendEmail(notificationDto);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		when(mailSenderService.sendEmail("recipient", "subject", "content")).thenReturn(false);
		res = mailSenderController.sendEmail(notificationDto);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}

}
