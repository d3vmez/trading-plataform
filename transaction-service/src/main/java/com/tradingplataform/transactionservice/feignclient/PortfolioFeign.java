package com.tradingplataform.transactionservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "portfolio-service")
public interface PortfolioFeign {

}
