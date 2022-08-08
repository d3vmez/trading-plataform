package com.tradingplataform.portfolioservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tradingplataform.portfolioservice.model.Portfolio;
import com.tradingplataform.portfolioservice.service.impl.PortfolioService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { PortfolioControllerTest.class })
class PortfolioControllerTest {

	@Mock
	PortfolioService portfolioService;
	
	@InjectMocks
	PortfolioController portfolioController;
	
	@Test @Order(1)
	void testGetAll() {

		List<Portfolio> portfolios = new ArrayList<>();
		Portfolio p1 = new Portfolio(1, "product name 1", 1, 10);
		Portfolio p2 = new Portfolio(2, "product name 2", 2, 20);
		portfolios.add(p1);
		portfolios.add(p2);
		Map<String, String> header = new HashMap<>();
		
		when(portfolioService.findAll()).thenReturn(portfolios);
		ResponseEntity<List<Portfolio>> res = portfolioController.getAll(header);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		portfolios.clear();
		when(portfolioService.findAll()).thenReturn(portfolios);
		res = portfolioController.getAll(header);
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
	}
	
	@Test @Order(2)
	void testGetById() {
		
		Optional<Portfolio> p1 = Optional.of(new Portfolio(1, "product name 1", 1, 10));
		
		when(portfolioService.find(1)).thenReturn(p1);
		ResponseEntity<Portfolio> res = portfolioController.getById(1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		p1 = Optional.empty();
		when(portfolioService.find(1)).thenReturn(p1);
		res = portfolioController.getById(1);
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
		
	}
	
	@Test @Order(3)
	void testGetByUserId() {
		
		List<Portfolio> portfolios = new ArrayList<>();
		Portfolio p1 = new Portfolio(1, "product name 1", 1, 10);
		Portfolio p2 = new Portfolio(2, "product name 2", 2, 20);
		portfolios.add(p1);
		portfolios.add(p2);
		
		when(portfolioService.findAllByUserId(1)).thenReturn(portfolios);
		ResponseEntity <List<Portfolio>> res = portfolioController.getByUserId(1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		portfolios.clear();
		when(portfolioService.findAllByUserId(1)).thenReturn(portfolios);
		res =  portfolioController.getByUserId(1);
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
	}
	
	@Test @Order(4)
	void testSave() {
		
		Portfolio p1 = new Portfolio(1, "product name 1", 1, 10);
		when(portfolioService.save(p1)).thenReturn(p1);
		ResponseEntity<Portfolio> res = portfolioController.save(p1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}

}
