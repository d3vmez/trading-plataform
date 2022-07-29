package com.tradingplataform.transactionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.model.dto.TransactionDTO;
import com.tradingplataform.transactionservice.service.impl.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	@GetMapping("")
	public ResponseEntity<Transaction> doTransaction(@RequestBody TransactionDTO dto, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
	
		Transaction transaction = transactionServiceImpl.buy(dto);
		
		if(transaction == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(transaction);
	}
	
}
