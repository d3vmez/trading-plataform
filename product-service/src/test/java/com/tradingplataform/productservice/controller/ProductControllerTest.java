package com.tradingplataform.productservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.tradingplataform.productservice.model.Product;
import com.tradingplataform.productservice.service.impl.ProductService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ProductControllerTest.class })
class ProductControllerTest {

	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	@Test @Order(1)
	void testGetAll() {
		
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		Product p2 = new Product(2,"producto 2", new BigDecimal(20),20,2);
		products.add(p1);
		products.add(p2);

		when(productService.findAll()).thenReturn(products);
		ResponseEntity<List<Product>> res = productController.getAll();	
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		products.clear();
		res = productController.getAll();	
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
	}

	@Test @Order(2)
	void testGetById() {
		
		Optional<Product> p1 = Optional.of(new Product(1,"producto 1", new BigDecimal(10),1,10));
		when(productService.find(1)).thenReturn(p1);
		ResponseEntity<Product> res = productController.getById(1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals("producto 1", res.getBody().getName());
		
		p1 = Optional.empty();
		when(productService.find(1)).thenReturn(p1);
		res = productController.getById(1);
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
	
	}
	
	@Test @Order(3)
	void testSave() {
		
		String token = "token";
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		Map<String, String> header = new HashMap<String, String>();
		when(productService.save(p1, token)).thenReturn(p1);
		ResponseEntity<Product> res = productController.save(p1,header);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}
	
	@Test @Order(4)
	void testGetProducts() {
		
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),1,1);
		Product p2 = new Product(2,"producto 2", new BigDecimal(20),1,2);
		products.add(p1);
		products.add(p2);
		
		when(productService.findByUserId(1)).thenReturn(products);
		ResponseEntity<List<Product>> res = productController.getProducts(1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		products.clear();
		res = productController.getProducts(1);
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
		
	}
		
}
