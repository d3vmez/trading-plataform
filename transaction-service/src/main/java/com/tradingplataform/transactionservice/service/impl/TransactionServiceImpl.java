package com.tradingplataform.transactionservice.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.transactionservice.feignclient.NotificationFeign;
import com.tradingplataform.transactionservice.feignclient.Product;
import com.tradingplataform.transactionservice.feignclient.ProductFeign;
import com.tradingplataform.transactionservice.feignclient.User;
import com.tradingplataform.transactionservice.feignclient.UserFeignClient;
import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.model.dto.NotificationDTO;
import com.tradingplataform.transactionservice.model.dto.TransactionDTO;
import com.tradingplataform.transactionservice.repository.TransactionRespository;
import com.tradingplataform.transactionservice.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	TransactionRespository transactionRespository;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private ProductFeign productFeign;
	
	@Autowired
	private NotificationFeign notificationFeign;

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
	public Transaction buy(TransactionDTO dto, String token) {
		

		//Obtener producto
		Product product;
		try {
			product = productFeign.getProduct(dto.getIdProduct());
		} catch (Exception e) {
			return null;
		}
		
		//Obtener precio del producto
		BigDecimal price = product.getPrice();
		
		// Obtener la cantidad que el comprador ha introducido
		int cuantity = dto.getCuantity();
		
		// Obtener si el producto tiene la cantidad necesaria;
		boolean hasCuantity = productFeign.productHasCuantity(product.getId(), cuantity);
		
		if(!hasCuantity) {
			return null;
		}
		
		// Comprobar si el comprador tiene balance para realizar la transacción
		if(!this.hasBalance(price, token)) {
			return null;
		}
		
		//Obtener comprador y vendedor
		User buyer;
		User seller;
	
		int idBuyer;
		try {
			idBuyer = userFeignClient.getId(token);
			buyer = userFeignClient.getUser(idBuyer);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return null;
		}
		
		try {
			seller = userFeignClient.getUser(dto.getIdSeller());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		// TODO Comprabar que el comprador tiene saldo suficiente
		// TODO Comprobar que el producto tiene cantidad suficiente

		
		//Cambio de propietario
		product.setUserId(buyer.getId());
		
		// Precio transaccion
		BigDecimal totalPrice = BigDecimal.valueOf(cuantity).multiply(price);
		
		//Resta de saldo al comprador
		BigDecimal updatedBalanceBuyer = updatedBalanceBuyer = buyer.getBalance().subtract(totalPrice);

		//Suma de saldo al vendedor
		BigDecimal updatedBalanceSeller = 	seller.getBalance().add(totalPrice);

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
		
		// Actualizar balances del comprador y del vendedor
		try {
			User sellerUpdated = userFeignClient.updateUserBalance(updatedBalanceSeller, seller.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		try {
			User buyerUpdated = userFeignClient.updateUserBalance(updatedBalanceBuyer, idBuyer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		try {
			Product productUpdated = productFeign.updateProduct(product.getId(), cuantity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		// Envio de notificaion al comprador
		if(!sendMailNotification(buyer.getEmail())) {
			System.out.println("error al enviar el email");
		}
		
		return transaction;

	}

	private boolean hasBalance(BigDecimal balance, String token) {

		if (userFeignClient.hasBalance(balance, token)) {
			return true;
		}

		return false;
	}
	
	private boolean sendMailNotification(String email) {
		
		NotificationDTO notificationDTO = new NotificationDTO(email, "Completed transaction", "Your buy order is terminated");
		boolean mail = notificationFeign.sendEmail(null);
		
		if(mail) {
			return true;
		}
		
		return false;
		
	}
}
