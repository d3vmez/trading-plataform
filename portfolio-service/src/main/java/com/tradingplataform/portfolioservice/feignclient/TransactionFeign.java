package com.tradingplataform.portfolioservice.feignclient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tradingplataform.portfolioservice.model.PortfolioDTO;

@FeignClient(name = "transaction-service")
public interface TransactionFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/transaction/sell")
	public Transaction doSell(@RequestBody PortfolioDTO dto, BindingResult bindingResult, @RequestHeader Map<String, String> header);
}
