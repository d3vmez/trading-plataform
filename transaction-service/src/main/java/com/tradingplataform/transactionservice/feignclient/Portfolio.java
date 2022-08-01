package com.tradingplataform.transactionservice.feignclient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private int idUser;
	private int cuantity;
}
