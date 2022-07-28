package com.tradingplataform.security;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import com.tradingplataform.authservice.security.JwtEntryPoint;

class JwtEntryPointTest {
	
	JwtEntryPoint jwtEntryPoint;

	@BeforeEach
	void setUp() throws Exception {
		
		jwtEntryPoint = new JwtEntryPoint();
	}

	@Test
	void testCommence() throws IOException, ServletException {
		
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationCredentialsNotFoundException ex = new AuthenticationCredentialsNotFoundException("");

        jwtEntryPoint.commence(request, response, ex);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
	}

}
