package com.tradingplataform.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SecurityUserTest {

	SecurityUser securityUser;

	@BeforeEach
	void setUp() throws Exception {

		securityUser = new SecurityUser("marcos@marcos.com", "123");
	}

	@Test
	void testSecurityUser() {
		SecurityUser securityUser = new SecurityUser("marcos@marcos.com", "123");
		assertAll("Test SecurityUser", () -> assertEquals("marcos@marcos.com", securityUser.getUsername()),
				() -> assertEquals("123", securityUser.getPassword()));
	}

	@Test
	void testBuild() {
		User user = new User("marcos@marcos.com", "123");
		SecurityUser result = SecurityUser.build(user);

		assertAll("Test build", () -> assertEquals("marcos@marcos.com", result.getUsername()),
				() -> assertEquals("123", result.getPassword()));

	}

	@Test
	void testGetAuthorities() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		assertAll("Test get", () -> assertEquals("marcos@marcos.com", securityUser.getUsername()),
				() -> assertEquals("123", securityUser.getPassword()));
	}

	@Test
	void testIsAccountNonExpired() {
		assertEquals(true, securityUser.isAccountNonExpired());
	}

	@Test
	void testIsAccountNonLocked() {
		assertEquals(true, securityUser.isAccountNonLocked());
	}

	@Test
	void testIsCredentialsNonExpired() {
		assertEquals(true, securityUser.isCredentialsNonExpired());
	}

	@Test
	void testIsEnabled() {
		assertEquals(true, securityUser.isEnabled());
	}

}
