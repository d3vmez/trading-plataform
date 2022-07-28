package com.tradingplataform.authservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	
	// Constructors
	/////////////////////////////////////////////////////////////////
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(@NotNull String email, @NotNull String password) {
		super();
		this.email = email;
		this.password = password;
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
	
//	@Override
//	public String toString() {
//		return "Client [id=" + id + ", email=" + email + ", password=" + password + "]";
//	}
	
}
