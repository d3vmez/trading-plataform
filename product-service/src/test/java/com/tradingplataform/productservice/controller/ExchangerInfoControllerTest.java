package com.tradingplataform.productservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tradingplataform.productservice.model.Product;
import com.tradingplataform.productservice.service.impl.ExchangeInfoService;
import com.tradingplataform.productservice.service.impl.ProductService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ExchangerInfoControllerTest.class })
class ExchangerInfoControllerTest {

	@Mock
	ProductService productService;
	
	@Mock
	ExchangeInfoService exchangeInfoService;
	
	@InjectMocks
	ExchangerInfoController exchangerInfoController;
	
	@Test @Order(1)
	void testGetProductById() {

		Optional<Product> p1 = Optional.of(new Product(1,"producto 1", new BigDecimal(10),1,10));
		when(productService.find(1)).thenReturn(p1);
		ResponseEntity<Product> res = exchangerInfoController.getProductById(1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		p1 = null;
		when(productService.find(1)).thenReturn(p1);
		res = exchangerInfoController.getProductById(1);
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
		
	}
	
	@Test @Order(2)
	void testProductHasCuantity() {
		
		boolean hasCuantity = true;
		when(exchangeInfoService.productHasCuantity(1, 1)).thenReturn(hasCuantity);
		ResponseEntity<Boolean> res = exchangerInfoController.productHasCuantity(1, 1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}

	@Test @Order(3)
	void testUpdateProduct() {
		
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		when(exchangeInfoService.updateProductCuantity(1, 1)).thenReturn(p1);
		ResponseEntity<Product> res = exchangerInfoController.updateProduct(1, 1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		p1 = null;
		when(exchangeInfoService.updateProductCuantity(1, 1)).thenReturn(p1);
		res = exchangerInfoController.updateProduct(1, 1);
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
		
	}
	
	@Test @Order(4)
	void testGetProductByName() {
		
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		when(productService.findByName("producto 1")).thenReturn(p1);
		ResponseEntity<Product> res = exchangerInfoController.getProductByName("producto 1");
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		p1 = null;
		when(productService.findByName("producto 1")).thenReturn(p1);
		res = exchangerInfoController.getProductByName("producto 1");
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
	}
}
