package com.tradingplataform.transactionservice.service;

import java.util.List;
import java.util.Optional;

import com.tradingplataform.transactionservice.model.Transaction;

public interface ITransactionService {
	
	public Optional<Transaction> findById(int id);
	
	public List<Transaction> findAll();
	
	public Transaction save(Transaction transaction);
	
	public void delete(int id);
	
	public Transaction buy();

}
