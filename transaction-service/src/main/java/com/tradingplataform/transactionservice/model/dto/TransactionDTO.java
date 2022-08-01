package com.tradingplataform.transactionservice.model.dto;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TransactionDTO {
	
	@NotBlank
	private int idSeller;
	@NotBlank
	private int idProduct;
	@NotBlank
	private int cuantity;

}
