package com.tradingplataform.authservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.models.dto.LoginDto;
import com.tradingplataform.authservice.models.dto.RegisterDto;
import com.tradingplataform.authservice.security.JwtProvider;
import com.tradingplataform.authservice.service.impl.AuthUserService;
import com.tradingplataform.authservice.service.impl.UserService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { AuthUserServiceTest.class })
class AuthUserServiceTest {

	@Mock
	UserService userService;

	@Mock
	JwtProvider jwtProvider;

	@Mock
	BCryptPasswordEncoder passwordEncoder;
	
	@InjectMocks
	AuthUserService authUserService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void testRegister() {

		// Datos que no existen en la bd
		RegisterDto registerDto = new RegisterDto("marcos@email.com", "123");
		// Datos que si existen en la bd
		RegisterDto registerDto2 = new RegisterDto("pablo@email.com", "123");

		// Se crea un usuario a partir del dto recibido
		User user = new User(registerDto.getEmail(), registerDto.getPassword());
		user.setBalance(BigDecimal.ZERO);

		// Optional con usuario
		Optional<User> opUser = Optional.of(new User("pablo@email.com", "123"));

		// Si el email no existe
		when(userService.findByEmail(registerDto.getEmail())).thenReturn(Optional.empty());
		// Si el email existe
		when(userService.findByEmail(registerDto2.getEmail())).thenReturn(opUser);

		String passwordEncode = "$2y$10$6ZOnhQAnDQfjwAryjJ7gluc3orEsu58SmC2g5Lvgo9abOzpYqaqSW";
		when(passwordEncoder.encode(registerDto.getPassword())).thenReturn(passwordEncode);

		when(userService.save(user)).thenReturn(user);

		assertAll("register", 
				() -> assertEquals(user.getEmail(), authUserService.register(registerDto).getEmail()),
				() -> assertEquals(null, authUserService.register(registerDto2)
						));

	}

	@Test
	@Order(2)
	void testLogin() {
		
		// Datos que existen en la bd
		LoginDto loginDto = new LoginDto("marcos@marcos.com", "123");
		String passEncode = "$2y$10$KtyJcmaHYnSg6eH4x9YJfe0n5jAYOaspfsnthxCAr7eDA.mMUxlr.";
		Optional<User> user = Optional.of(new User("marcos@marcos.com", passEncode));
		
		// Existe en la bd pero introduce la contraseña mal
		LoginDto loginDto3 = new LoginDto("marcos@marcos.com", "3455");
		
		
		
		// Datos que no existen en la bd
		LoginDto loginDto2 = new LoginDto("pablo@pablo.com", "123");
		
		// Comprobar que el usuario existe en la base de datos
		when(userService.findByEmail(loginDto.getEmail())).thenReturn(user);
		when(userService.findByEmail(loginDto2.getEmail())).thenReturn(Optional.empty());
		
		when(passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())).thenReturn(true);
		when(passwordEncoder.matches(loginDto3.getPassword(), user.get().getPassword())).thenReturn(false);
		
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbSIsImlhdCI6MTY1OTY4MjAwMSwiZXhwIjoxNjU5Njg1NjAxfQ.b4Ho8DxgLxd8Aib3yJshrVg3dzXOSbd2xCdJM-Uk0woLP8qJKnToZ9qQOlpnXfaH";
		when(jwtProvider.createToken(loginDto.getEmail())).thenReturn(token);
		
		
		
		assertAll("login",
				()->assertEquals(null, authUserService.login(loginDto2)),
				()->assertEquals(token, authUserService.login(loginDto).getToken()),
				()->assertEquals(null, authUserService.login(loginDto3))
		
				);
	
	}

	@Test
	@Order(3)
	void testValidateToken() {
		
		String validToken = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJjb3NAbWFyY29zLmNvbSIsImlhdCI6MTY1OTY4MjAwMSwiZXhwIjoxNjU5Njg1NjAxfQ.b4Ho8DxgLxd8Aib3yJshrVg3dzXOSbd2xCdJM-Uk0woLP8qJKnToZ9qQOlpnXfaH";
		String invalidToken = "eyJhbGciOiJIUzM4NCJ9";
		// El token es válido pero el subject de las claims no existen en bd
		String invalidToken2 = "eyJhbGciOiJIUzM4NCJ9.eyyy";
		String email = "marcos@marcos.com";
		String invalidEmail = "pepe@pepe.com";
		
		Optional<User> opUser = Optional.of(new User(email, "123"));
		when(jwtProvider.isValidateToken(validToken)).thenReturn(true);
		when(jwtProvider.isValidateToken(invalidToken)).thenReturn(false);
		when(jwtProvider.isValidateToken(invalidToken2)).thenReturn(true);
		
		when(jwtProvider.getEmailFromToken(validToken)).thenReturn(email);
		when(jwtProvider.getEmailFromToken(invalidToken2)).thenReturn(invalidEmail);
		
		when(userService.findByEmail(email)).thenReturn(opUser);
		when(userService.findByEmail(invalidEmail)).thenReturn(Optional.empty());
		
		assertAll("validaToken",
				()->assertEquals(validToken, authUserService.validateToken(validToken).getToken()),
				()->assertEquals(null, authUserService.validateToken(invalidToken)),
				()->assertEquals(null, authUserService.validateToken(invalidToken2))
				);
	}

}
