package com.tradingplataform.transactionservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.repository.TransactionRespository;
import com.tradingplataform.transactionservice.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	TransactionRespository transactionRespository;
	
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
	public Transaction buy() {
		return null;
	}

	private boolean hasBalance() {
		return true;
	}
}
