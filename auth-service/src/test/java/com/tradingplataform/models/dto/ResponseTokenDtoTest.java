package com.tradingplataform.models.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tradingplataform.authservice.models.dto.ResponseTokenDto;

class ResponseTokenDtoTest {

	ResponseTokenDto responseDto;

	@BeforeEach
	void setUp() throws Exception {
		responseDto = new ResponseTokenDto();
	}

	@Test
	void testResponseDto() {
		ResponseTokenDto responseDto = new ResponseTokenDto();
		assertAll("ResponseDto()", () -> assertEquals(null, responseDto.getToken()),
				() -> assertEquals("Bearer", responseDto.getBearer()));
	}

	@Test
	void testResponseDtoStringString() {
		ResponseTokenDto responseDto = new ResponseTokenDto("token", "userName");

		assertAll("ResponseDto()", () -> assertEquals("token", responseDto.getToken()),
				() -> assertEquals("Bearer", responseDto.getBearer()),
				() -> assertEquals("userName", responseDto.getUserName()));
	}

	@Test
	void testGetToken() {
		responseDto.setToken("token2");
		responseDto.setUserName("username2");
		responseDto.setBearer("Bearer2");

		assertAll("ResponseDto()", () -> assertEquals("token2", responseDto.getToken()),
				() -> assertEquals("Bearer2", responseDto.getBearer()),
				() -> assertEquals("username2", responseDto.getUserName()));
	}

}
