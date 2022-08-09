package com.tradingplataform.authservice.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	// Attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@Min(value = 0)
	private BigDecimal balance;
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	// Constructors
	/////////////////////////////////////////////////////////////////
	public User() {

	}

	public User(@NotNull String email, @NotNull String password) {
		this.email = email;
		this.password = password;
	}
	
	
	public User(@NotNull String email, @NotNull String password, @Min(0) BigDecimal balance) {
		this.email = email;
		this.password = password;
		this.balance = balance;
	}

	// Access methods
	/////////////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/////////////////////////////////////////////////////////////////
		
}
