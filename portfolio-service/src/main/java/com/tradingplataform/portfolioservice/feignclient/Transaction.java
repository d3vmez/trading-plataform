package com.tradingplataform.portfolioservice.feignclient;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Transaction {

	private int idBuyer;
	private int idSeller;
	private int idProduct;
	private int cuantity;
	private BigDecimal price;
	private Date date;
	
}
