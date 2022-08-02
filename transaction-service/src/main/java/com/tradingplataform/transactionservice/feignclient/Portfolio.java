package com.tradingplataform.transactionservice.feignclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

	private String productName;
	private int userId;
	private int cuantity;
}
