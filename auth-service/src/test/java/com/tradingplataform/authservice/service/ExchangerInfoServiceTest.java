package com.tradingplataform.authservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.context.SpringBootTest;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.security.JwtProvider;
import com.tradingplataform.authservice.service.impl.ExchangerInfoService;
import com.tradingplataform.authservice.service.impl.UserService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ExchangerInfoServiceTest.class})
class ExchangerInfoServiceTest {
	
	@Mock
	JwtProvider jwtProvider;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	ExchangerInfoService exchangerInfoService;  
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testGetIdFromToken() {
		
		String validToken = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbSIsImlhdCI6MTY1OTY4MjAwMSwiZXhwIjoxNjU5Njg1NjAxfQ.b4Ho8DxgLxd8Aib3yJshrVg3dzXOSbd2xCdJM-Uk0woLP8qJKnToZ9qQOlpnXfaH";
		String invalidToken = "eyJhbGciOiJIUzM4NCJ9";
		// Token que contiene un usuario que no existe en el sistema
		String validToken2  = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbS";
		
		String email = "marcos@marcos.com";
		String email2 = "pepe@pepe.com";
		User user = new User();
		user.setEmail(email);
		user.setId(1);
		Optional<User> opUser = Optional.of(user);
		
		when(jwtProvider.isValidateToken(validToken)).thenReturn(true);
		when(jwtProvider.isValidateToken(invalidToken)).thenReturn(false);
		when(jwtProvider.isValidateToken(validToken2)).thenReturn(true);
		
		when(jwtProvider.getEmailFromToken(validToken2)).thenReturn(email2);
		when(jwtProvider.getEmailFromToken(validToken)).thenReturn(email);
		
		when(userService.findByEmail(email)).thenReturn(opUser);
		when(userService.findByEmail(email2)).thenReturn(Optional.empty());
		
		assertAll("getIdFromToken", 
				()->assertEquals(1, exchangerInfoService.getIdFromToken(validToken)),
				()->assertEquals(null, exchangerInfoService.getIdFromToken(invalidToken)),
				()->assertEquals(null, exchangerInfoService.getIdFromToken(validToken2))
				);
			
	}

	@Test
	@Order(2)
	void testUserHasBalance() {
		
		// En este método a userBalance se le pasa el token con Bearer
		// Pero a al ahora de validar isValidate se le pasa el token sin el bearer
		// para simular la operacion
		
		String nullToken = null;
		
		String bearerToken = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbSIsImlhdCI6MTY1OTY4MjAwMSwiZXhwIjoxNjU5Njg1NjAxfQ.b4Ho8DxgLxd8Aib3yJshrVg3dzXOSbd2xCdJM-Uk0woLP8qJKnToZ9qQOlpnXfaH";
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbSIsImlhdCI6MTY1OTY4MjAwMSwiZXhwIjoxNjU5Njg1NjAxfQ.b4Ho8DxgLxd8Aib3yJshrVg3dzXOSbd2xCdJM-Uk0woLP8qJKnToZ9qQOlpnXfaH";
		String email = "marcos@marcos.com";
		User user = new User();
		user.setEmail(email);
		user.setId(1);
		user.setBalance(new BigDecimal(1000));
		Optional<User> opUser = Optional.of(user);
		
		when(jwtProvider.isValidateToken(token)).thenReturn(true);
		when(jwtProvider.getEmailFromToken(token)).thenReturn(email);
		when(userService.findByEmail(email)).thenReturn(opUser);
		
		assertAll("userHasBalance",
				()->assertEquals(true, exchangerInfoService.userHasBalance(new BigDecimal(500), bearerToken)),
				()->assertEquals(false, exchangerInfoService.userHasBalance(new BigDecimal(1001), bearerToken)),
				()->assertEquals(false, exchangerInfoService.userHasBalance(new BigDecimal(1001), null))
				);
			
	}

}
