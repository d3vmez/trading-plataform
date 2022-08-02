package com.tradingplataform.transactionservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "portfolio-service")
public interface PortfolioFeign {

	@RequestMapping(method = RequestMethod.GET, value = "/portfolio/{id}")
	public Portfolio getById(@PathVariable("id") int id);
	
	@RequestMapping(method = RequestMethod.GET, value = "/portfolio/byuser/{userId}")
	public List<Portfolio> getByUserId(@PathVariable("userId") int userId);
	
	@RequestMapping(method = RequestMethod.POST, value = "/portfolio")
	public Portfolio save(@RequestBody Portfolio portfolio);
}
