package com.tradingplataform.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradingplataform.transactionservice.model.Transaction;

@Repository
public interface TransactionRespository extends JpaRepository<Transaction, Integer>{
	
}
