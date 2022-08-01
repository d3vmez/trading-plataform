package com.tradingplataform.transactionservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tradingplataform.transactionservice.model.dto.NotificationDTO;



@FeignClient(name = "notification-service")
public interface NotificationFeign {
	
	@RequestMapping(method = RequestMethod.POST ,value = "/notification/mail")
	public Boolean sendEmail(@RequestBody NotificationDTO notificationDto);

}
