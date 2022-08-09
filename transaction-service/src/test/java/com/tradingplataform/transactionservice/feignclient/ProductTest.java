package com.tradingplataform.transactionservice.feignclient;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ProductTest.class })
class ProductTest {

	@Test @Order(1)
	void testProductModel() {
		
		Product p1 = new Product(1,"producto", new BigDecimal(10),10);
		Product p2 = new Product();
		p2.setId(2);
		p2.setName("producto");
		p2.setPrice(new BigDecimal(10));
		p2.setUserId(10);
		
		assertAll("Test getters and setters",
				()-> assertNotEquals(p1.getId(), p2.getId()),
				()-> assertEquals(p1.getName(), p2.getName()),
				()-> assertEquals(p1.getPrice(), p2.getPrice()),
				()-> assertEquals(p1.getUserId(), p2.getUserId())
				);
		
		assertEquals(p1.equals(p2), false);
		assertEquals("Product(id=1, name=producto, price=10, userId=10)", p1.toString());
		assertEquals(919982596, p1.hashCode());
	}

}

