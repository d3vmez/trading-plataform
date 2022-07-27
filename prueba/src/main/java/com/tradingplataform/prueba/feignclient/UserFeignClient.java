package com.tradingplataform.prueba.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth-clients")
public interface UserFeignClient {

}
