package com.tradingplataform.productservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ProductTest.class })
class ProductTest {

	@Test @Order(1)
	void testProductModel() {
		
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		Product p2 = new Product();
		p2.setId(1);
		p2.setName("producto 2");
		p2.setPrice(new BigDecimal(10));
		p2.setUserId(10);
		p2.setCuantity(1);
		
		assertAll("Test getters and setters",
				()-> assertEquals(p1.getId(), p2.getId()),
				()-> assertNotEquals(p1.getName(), p2.getName()),
				()-> assertEquals(p1.getPrice(), p2.getPrice()),
				()-> assertEquals(p1.getUserId(), p2.getUserId()),
				()-> assertEquals(p1.getCuantity(), p2.getCuantity())
				);
		
		assertEquals(p1.equals(p2), false);
		assertEquals("Product(id=1, name=producto 1, price=10, userId=10, cuantity=1)", p1.toString());
	}

}
