package com.tradingplataform.transactionservice.feignclient;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { UserTest.class })
class UserTest {

	@Test @Order(1)
	void testUserModel() {

		User u1 = new User(1, "email", "pass", new BigDecimal(100));
		User u2 = new User();
		u2.setId(2);
		u2.setEmail("email");
		u2.setPassword("pass");
		u2.setBalance(new BigDecimal(100));
		
		assertAll("Test getters and setters",
				()-> assertNotEquals(u1.getId(), u2.getId()),
				()-> assertEquals(u1.getEmail(), u2.getEmail()),
				()-> assertEquals(u1.getPassword(), u2.getPassword()),
				()-> assertEquals(u1.getBalance(), u2.getBalance())
				);
		
		assertEquals(u1.equals(u2), false);
		assertEquals("User(id=1, email=email, password=pass, balance=100)", u1.toString());
		assertEquals(1539653623, u1.hashCode());
		
	}

}
