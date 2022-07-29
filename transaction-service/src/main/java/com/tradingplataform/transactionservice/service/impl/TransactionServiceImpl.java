package com.tradingplataform.transactionservice.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.transactionservice.feignclient.Product;
import com.tradingplataform.transactionservice.feignclient.ProductFeign;
import com.tradingplataform.transactionservice.feignclient.User;
import com.tradingplataform.transactionservice.feignclient.UserFeignClient;
import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.model.dto.TransactionDTO;
import com.tradingplataform.transactionservice.repository.TransactionRespository;
import com.tradingplataform.transactionservice.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	TransactionRespository transactionRespository;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductFeign productFeign;
	
	@Override
	public Optional<Transaction> findById(int id) {
		return transactionRespository.findById(id);
	}

	@Override
	public List<Transaction> findAll() {
		return transactionRespository.findAll();
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRespository.save(transaction);
	}

	@Override
	public void delete(int id) {
		transactionRespository.deleteById(id);
	}

	@Override
	public Transaction buy(TransactionDTO dto) {
		
		//Obtener producto
		Product product = productFeign.getProduct(dto.getIdProduct());
		
		//Obtener precio y cantidad
		BigDecimal price = product.getPrice();
		int cuantity = dto.getCuantity();
		
		//Obtener comprador y vendedor
		User buyer = userFeignClient.getUser(dto.getIdBuyer());
		User seller = userFeignClient.getUser(dto.getIdSeller());
		
		//Cambio de propietario
		product.setUserId(buyer.getId());
		
		// Precio transaccion
		BigDecimal totalPrice = BigDecimal.valueOf(cuantity).multiply(price);
		
		//Resta de saldo al comprador
		BigDecimal updatedBalanceBuyer = buyer.getBalance().subtract(totalPrice);
		
		//Suma de saldo al vendedor
		BigDecimal updatedBalanceSeller = 	seller.getBalance().add(totalPrice);
		
		//Enviar usuarios actualizados
		userFeignClient.updateUserBalance(updatedBalanceBuyer, buyer.getId());
		
		//Enviar usuarios actualizados
		userFeignClient.updateUserBalance(updatedBalanceSeller, seller.getId());
		
		Transaction transaction = new Transaction();
		transaction.setCuantity(cuantity);
		transaction.setPrice(product.getPrice());
		
		//Convertir util.Date a sql.Date
		//Añadir fecha al pedido
		java.util.Date fecha = new java.util.Date();
		java.sql.Date fechaSQl = new Date(fecha.getTime());
		
		transaction.setDate(fechaSQl);
		transaction.setIdBuyer(buyer.getId());
		transaction.setIdSeller(seller.getId());
		transaction.setIdProduct(product.getId());

		this.save(transaction);
		
		return transaction;

	}

	private boolean hasBalance(BigDecimal balance, String token) {
		
		if(userFeignClient.hasBalance(balance, token)) {
			return true;
		}
		
		return false;
	}
}
