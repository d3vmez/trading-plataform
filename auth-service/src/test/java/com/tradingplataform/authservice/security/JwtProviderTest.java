package com.tradingplataform.authservice.security;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@TestInstance(Lifecycle.PER_CLASS)
class JwtProviderTest {
	
	
	JwtProvider jwtProvider;

	
	@BeforeAll
	void setUp() throws Exception {
	
	jwtProvider = new JwtProvider();	
	byte[] keyBytes = Decoders.BASE64.decode("8VXw8doXSPtvlenHPrrH3zU7TU5Yq7Qr8VXw8doXSPtvlenHPrrH3zU7TU5Yq7Qr");
	jwtProvider.setSecretKey(Keys.hmacShaKeyFor(keyBytes));	
		
	}
	
	@Test
	void testCreateToken() {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbSIsImlhdCI6MTY1OTY4NzUyNywiZXhwIjoxNjU5NjkxMTI3fQ.D9dUTpCn6-p4L6572mhKAJwCcyVHgwW6otzAxmdQXxrY9nRJwG2H4jBkIII-9UEj";
		System.out.println(jwtProvider.createToken("marcos@marcos.com"));
	}

	@Test
	void testGetEmailFromToken() {
		fail("Not yet implemented");
	}

	@Test
	void testIsValidateToken() {
		fail("Not yet implemented");
	}

}
