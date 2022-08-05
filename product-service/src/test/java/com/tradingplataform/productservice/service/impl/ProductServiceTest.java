package com.tradingplataform.productservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.tradingplataform.productservice.feignclient.UserFeignClient;
import com.tradingplataform.productservice.model.Product;
import com.tradingplataform.productservice.repository.ProductRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ProductServiceTest.class })
class ProductServiceTest {

	@Mock
	ProductRepository productRepository;
	
	@Mock
	UserFeignClient userFeignClient;
	
	@InjectMocks 
	ProductService productService;
	
	@Test @Order(1)
	void testFindAll() {
		
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		Product p2 = new Product(2,"producto 2", new BigDecimal(20),20,2);
		products.add(p1);
		products.add(p2);
		
		when(productRepository.findAll()).thenReturn(products);
		assertEquals(products, productService.findAll());
	}
	
	@Test @Order(2)
	void testFind() {

		Optional<Product> p1 =  Optional.of(new Product (1,"producto 1", new BigDecimal(10),10,1));
		
		when(productRepository.findById(1)).thenReturn(p1);
		assertEquals(p1, productService.find(1));
	}

	@Test @Order(3)
	void testSave() {
		
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		
		when(productRepository.save(p1)).thenReturn(p1);
		assertEquals(p1, productService.save(p1, "token"));
		
		when(userFeignClient.getId("token")).thenReturn(null);
		when(productService.save(p1, "token")).thenReturn(null);
		assertEquals(null, productService.save(p1, "token"));
		
	}

	@Test @Order(4)
	void testDelete() {
		
		productService.delete(1);
		verify(productRepository,times(1)).deleteById(1);
		
	}
	
	@Test @Order(5)
	void tesFindByUserId() {
		
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		Product p2 = new Product(2,"producto 2", new BigDecimal(20),20,2);
		products.add(p1);
		products.add(p2);
		
		when(productRepository.findByUserId(1)).thenReturn(products);
		assertEquals(products, productService.findByUserId(1));
	}
	
	@Test @Order(6)
	void testUpdate() {
		
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		
		when(productRepository.save(p1)).thenReturn(p1);
		assertEquals(p1, productService.update(p1));
		
	}
	
	@Test @Order(7)
	void tesFindByName() {
	
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product(1,"producto 1", new BigDecimal(10),10,1);
		Product p2 = new Product(2,"producto 2", new BigDecimal(20),20,2);
		products.add(p1);
		products.add(p2);
		
		when(productRepository.findByName("producto 1")).thenReturn(p1);
		assertEquals(p1, productService.findByName("producto 1"));
	}
	
}
