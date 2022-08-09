package com.tradingplataform.transactionservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tradingplataform.transactionservice.feignclient.NotificationFeign;
import com.tradingplataform.transactionservice.feignclient.PortfolioFeign;
import com.tradingplataform.transactionservice.feignclient.Product;
import com.tradingplataform.transactionservice.feignclient.ProductFeign;
import com.tradingplataform.transactionservice.feignclient.User;
import com.tradingplataform.transactionservice.feignclient.UserFeignClient;
import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.model.dto.NotificationDTO;
import com.tradingplataform.transactionservice.model.dto.PortfolioDTO;
import com.tradingplataform.transactionservice.model.dto.TransactionDTO;
import com.tradingplataform.transactionservice.repository.TransactionRespository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TransactionServiceImplTest.class })
class TransactionServiceImplTest {

	@Mock
	TransactionRespository transactionRespository;
	@Mock
	UserFeignClient userFeignClient;
	@Mock
	ProductFeign productFeign;
	@Mock
	PortfolioFeign feignPortfolio;
	@Mock
	NotificationFeign notificationFeign;
	
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	
	@Test @Order(1)
	void testFindById() {
		
		Date fecha = new Date(1);
		Optional<Transaction> transaction = Optional.of(new Transaction(1, 1, 2, 1, 10, new BigDecimal(100), fecha));
		
		when(transactionRespository.findById(1)).thenReturn(transaction);
		assertEquals(transaction, transactionServiceImpl.findById(1));
		
	}
	
	@Test @Order(2)
	void testFindAll() {
		
		List<Transaction> transactions = new ArrayList<>();
		Date fecha = new Date(1);
		Transaction t1 = new Transaction(1, 1, 2, 1, 10, new BigDecimal(100), fecha);
		Transaction t2 = new Transaction(2, 2, 1, 2, 10, new BigDecimal(100), fecha);
		transactions.add(t1);
		transactions.add(t2);
		
		when(transactionRespository.findAll()).thenReturn(transactions);
		assertEquals(transactions, transactionServiceImpl.findAll());
		
	}
	
	@Test @Order(3)
	void testSave() {
		
		Date fecha = new Date(1);
		Transaction transaction = new Transaction(1, 1, 2, 1, 10, new BigDecimal(100), fecha);
		
		when(transactionRespository.save(transaction)).thenReturn(transaction);
		assertEquals(transaction, transactionServiceImpl.save(transaction));
		
	}
	
	@Test @Order(4)
	void testDelete() {
		
		transactionServiceImpl.delete(1);
		verify(transactionRespository,times(1)).deleteById(1);;
		
	}
	
	@Test @Order(5)
	void testSell() {
		
		Date fecha = new Date(1);
		Transaction transaction = new Transaction(1, 1, 2, 1, 10, new BigDecimal(100), fecha);
		Product product = new Product(1, "name", new BigDecimal(100), 1);
		
		PortfolioDTO dto = new PortfolioDTO("name", 1, 10);
		String token = "token";
		
		when(productFeign.getProductByName("name")).thenReturn(null);
		assertEquals(null, transactionServiceImpl.sell(dto, token));
		
		when(productFeign.getProductByName("name")).thenReturn(product);
		when(transactionServiceImpl.sell(dto, token)).thenReturn(transaction);
		assertNotNull(transactionServiceImpl.sell(dto, token));
	
	}

	@Test @Order(8)
	void testBuy() {
		
		TransactionDTO dto = new TransactionDTO(1, 1, 10);
		Product product = new Product(1, "name", new BigDecimal(100), 1);
		User buyer = new User(1,"email","pass", new BigDecimal(100));
		User seller = new User(1,"email","pass", new BigDecimal(100));
		
		when(productFeign.getProduct(1)).thenReturn(product);
		when(productFeign.productHasCuantity(1, 10)).thenReturn(true);
		when(userFeignClient.hasBalance(product.getPrice(), "token")).thenReturn(true);
		when(userFeignClient.getId("token")).thenReturn(1);
		when(userFeignClient.getUser(1)).thenReturn(buyer);
		when(userFeignClient.updateUserBalance(new BigDecimal(10), 1)).thenReturn(buyer);
		when(userFeignClient.updateUserBalance(new BigDecimal(10), 2)).thenReturn(seller);
		when(productFeign.updateProduct(1, 10)).thenReturn(product);
		assertNotNull(transactionServiceImpl.buy(dto, "token"));
		
	}


}
