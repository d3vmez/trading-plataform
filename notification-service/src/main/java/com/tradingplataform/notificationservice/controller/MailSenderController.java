package com.tradingplataform.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.notificationservice.model.dto.NotificationDTO;
import com.tradingplataform.notificationservice.service.MailSenderService;

@RestController
@RequestMapping("/notification")
public class MailSenderController {

	@Autowired
	private MailSenderService mailSenderService;

	@PostMapping("/mail")
	public ResponseEntity<Boolean> sendEmail(@RequestBody NotificationDTO notificationDto) {

		boolean mail = mailSenderService.sendEmail(notificationDto.getRecipient(), notificationDto.getSubject(),
				notificationDto.getContent());
		if (!mail) {
			return ResponseEntity.ok(false);
		}

		return ResponseEntity.ok(true);

	}

}
