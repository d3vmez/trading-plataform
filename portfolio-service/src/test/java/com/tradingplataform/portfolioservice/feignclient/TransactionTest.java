package com.tradingplataform.portfolioservice.feignclient;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TransactionTest.class })
class TransactionTest {

	@Test @Order(1)
	void testTransactionModel() {
		
		Date fecha = new Date(0);
		Transaction t1 = new Transaction(1, 2, 1, 10, new BigDecimal(10), fecha);
		Transaction t2 = new Transaction();
		t2.setIdBuyer(1);
		t2.setIdSeller(2);
		t2.setIdProduct(1);
		t2.setCuantity(10);
		t2.setPrice(new BigDecimal(10));
		t2.setDate(fecha);
		
		assertAll("Test getters and setters",
				()-> assertEquals(t1.getIdBuyer(), t2.getIdBuyer()),
				()-> assertEquals(t1.getIdSeller(), t2.getIdSeller()),
				()-> assertEquals(t1.getIdProduct(), t2.getIdProduct()),
				()-> assertEquals(t1.getCuantity(), t2.getCuantity()),
				()-> assertEquals(t1.getPrice(), t2.getPrice()),
				()-> assertEquals(t1.getDate(), t2.getDate())
				);
		
		assertEquals(t1.equals(t2), true);
		assertEquals("Transaction(idBuyer=1, idSeller=2, idProduct=1, cuantity=10, price=10, date=1970-01-01)", t1.toString());
		assertEquals(-29721819,t1.hashCode());
	}

}
