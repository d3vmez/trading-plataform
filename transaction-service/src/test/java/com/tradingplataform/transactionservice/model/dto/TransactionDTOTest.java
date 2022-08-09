package com.tradingplataform.transactionservice.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TransactionDTOTest.class })
class TransactionDTOTest {

	@Test @Order(1)
	void testTransactionDTOModel() {

		TransactionDTO t1 = new TransactionDTO(1, 1, 10);
		TransactionDTO t2 = new TransactionDTO();
		t2.setIdSeller(2);
		t2.setIdProduct(1);
		t2.setCuantity(10);
		
		assertAll("Test getters and setters",
				()->assertNotEquals(t1.getIdSeller(), t2.getIdSeller()),
				()->assertEquals(t1.getIdProduct(), t2.getIdProduct()),
				()->assertEquals(t1.getCuantity(), t2.getCuantity())
				);
		
		assertEquals(t1.equals(t2), false);
		assertEquals("TransactionDTO(idSeller=1, idProduct=1, cuantity=10)", t1.toString());
		assertEquals(208929,t1.hashCode());
		
	}

}
