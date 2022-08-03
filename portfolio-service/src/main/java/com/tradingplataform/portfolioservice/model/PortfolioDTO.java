package com.tradingplataform.portfolioservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDTO {

	//private int id;
	private String productName;
	private int userId;
	private int cuantity;
}
