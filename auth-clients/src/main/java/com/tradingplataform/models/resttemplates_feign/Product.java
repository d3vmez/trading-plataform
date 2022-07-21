package com.tradingplataform.models.resttemplates_feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private String name;
	private double price;
	
	// Feign
	private int userId;
	

}
