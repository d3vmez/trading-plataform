package com.tradingplataform.transactionservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.model.dto.PortfolioDTO;
import com.tradingplataform.transactionservice.model.dto.TransactionDTO;
import com.tradingplataform.transactionservice.service.impl.TransactionServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TransactionControllerTest.class })
class TransactionControllerTest {

	
	@Mock
	TransactionServiceImpl transactionServiceImpl;
	
	@Mock
	BindingResult bindingResult;
	
	@InjectMocks
	TransactionController transactionController;
	
	@Test @Order(1)
	void testDoBuy() {

		TransactionDTO dto = new TransactionDTO(1, 1, 10);
		String token = "token";
		Date fecha = new Date(1);
		Transaction transaction = new Transaction(1, 1, 2, 1, 10, new BigDecimal(100), fecha);
		Map<String, String> header = new HashMap<>();
		
		when(transactionServiceImpl.buy(dto, token)).thenReturn(null);
		ResponseEntity<Transaction> res = transactionController.doBuy(dto, bindingResult, header);
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
		when(transactionServiceImpl.buy(dto, token)).thenReturn(transaction);
		res = transactionController.doBuy(dto, bindingResult, header);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(transaction, res.getBody());
		
	}
	
	@Test @Order(2)
	void testDoSell() {
		
		PortfolioDTO dto = new PortfolioDTO("name",1,10);
		String token = "token";
		Date fecha = new Date(1);
		Transaction transaction = new Transaction(1, 1, 2, 1, 10, new BigDecimal(100), fecha);
		Map<String, String> header = new HashMap<>();
		
		when(transactionServiceImpl.sell(dto, token)).thenReturn(null);
		ResponseEntity<Transaction> res = transactionController.doSell(dto, bindingResult, header);
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
		when(transactionServiceImpl.sell(dto, token)).thenReturn(transaction);
		res = transactionController.doSell(dto, bindingResult, header);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(transaction, res.getBody());
		
	}

}
