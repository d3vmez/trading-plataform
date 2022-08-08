package com.tradingplataform.portfolioservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tradingplataform.portfolioservice.model.Portfolio;
import com.tradingplataform.portfolioservice.repository.PortfolioRepository;

/**
 * @author pprieto
 *
 */

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { PortfolioServiceTest.class })
class PortfolioServiceTest {

	@Mock
	PortfolioRepository portfolioRepository;
	
	@InjectMocks
	PortfolioService portfolioService;
	
	@Test @Order(1)
	void testFindAll() {
		
		List<Portfolio> portfolios = new ArrayList<>();
		Portfolio p1 = new Portfolio(1, "product name 1", 1, 10);
		Portfolio p2 = new Portfolio(2, "product name 2", 2, 20);
		portfolios.add(p1);
		portfolios.add(p2);
		
		when(portfolioRepository.findAll()).thenReturn(portfolios);
		assertEquals(2, portfolioService.findAll().size());
		
	}
	
	@Test @Order(2)
	void testFind() {

		Optional<Portfolio> p1 = Optional.of(new Portfolio(1, "product name 1", 1, 10));
		
		when(portfolioRepository.findById(1)).thenReturn(p1);
		assertEquals(p1, portfolioService.find(1));
		
	}
	
	@Test @Order(3)
	void testSave() {
		
		Portfolio p1 = new Portfolio(1, "product name 1", 1, 10);
		
		when(portfolioRepository.save(p1)).thenReturn(p1);
		assertEquals(p1, portfolioService.save(p1));
		
	}
	
	@Test @Order(4)
	void testDelete() {
		
		portfolioService.delete(1);
		verify(portfolioRepository,times(1)).deleteById(1);;
		
	}
	
	@Test @Order(5)
	void testFindAllByUserId() {
		
		List<Portfolio> portfolios = new ArrayList<>();
		Portfolio p1 = new Portfolio(1, "product name 1", 1, 10);
		Portfolio p2 = new Portfolio(2, "product name 2", 2, 20);
		portfolios.add(p1);
		portfolios.add(p2);
		
		when(portfolioRepository.findAllByUserId(1)).thenReturn(portfolios);
		assertEquals(portfolios, portfolioService.findAllByUserId(1));
		
	}

}
