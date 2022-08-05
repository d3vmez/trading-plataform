package com.tradingplataform.productservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tradingplataform.productservice.model.Product;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ExchangeInfoServiceTest.class })
class ExchangeInfoServiceTest {

	@Mock
	ProductService productService;
	
	@InjectMocks
	ExchangeInfoService exchangeInfoService;
	
	@Test @Order(1)
	void testProductHasCuantity() {
		
		Optional<Product> p1 = Optional.of(new Product(1,"producto 1", new BigDecimal(10),1,10));
		when(productService.find(1)).thenReturn(p1);
		assertEquals(true, exchangeInfoService.productHasCuantity(1, 5));
		
		Optional<Product> p2 = Optional.empty();
		when(productService.find(1)).thenReturn(p2);
		assertEquals(false, exchangeInfoService.productHasCuantity(1, 5));

		when(productService.find(1)).thenReturn(p1);
		assertEquals(false, exchangeInfoService.productHasCuantity(1, 15));
		
	}
	
	@Test @Order(2)
	void testUpdateProductCuantity() {
		
		Optional<Product> p1 = Optional.of(new Product(1,"producto 1", new BigDecimal(10),1,10));
		when(productService.find(1)).thenReturn(p1);
		assertEquals(5, exchangeInfoService.updateProductCuantity(1, 5).getCuantity());
		
		
		Optional<Product> p2 = Optional.empty();
		when(productService.find(1)).thenReturn(p2);
		assertEquals(null, exchangeInfoService.updateProductCuantity(1, 5));
		
	}

}
