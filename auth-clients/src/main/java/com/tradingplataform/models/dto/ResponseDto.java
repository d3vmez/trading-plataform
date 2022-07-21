package com.tradingplataform.models.dto;

public class ResponseDto {
	
	private String token;
	private String bearer = "Bearer";
	private String userName;
	
	public ResponseDto() {
		
	}

	public ResponseDto(String token,String userName) {
		super();
		this.token = token;
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
