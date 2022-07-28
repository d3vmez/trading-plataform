package com.tradingplataform.transactionservice.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "transactions")
@Data @AllArgsConstructor @NoArgsConstructor
public class Transaction {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int idBuyer;
	@NotNull
	private int idSeller;
	@NotNull
	private int idProduct;
	@NotNull
	private int cuantity;
	@NotNull
	private BigDecimal price;
	
	private Date date;
	
}
