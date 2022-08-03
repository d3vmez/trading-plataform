package com.tradingplataform.transactionservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingplataform.transactionservice.model.Transaction;
import com.tradingplataform.transactionservice.model.dto.PortfolioDTO;
import com.tradingplataform.transactionservice.model.dto.TransactionDTO;
import com.tradingplataform.transactionservice.service.impl.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	@PostMapping()
	public ResponseEntity<Transaction> doBuy(@RequestBody TransactionDTO dto, BindingResult bindingResult, @RequestHeader Map<String, String> header){
		
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		String token = header.get("authorization");
	
		Transaction transaction = transactionServiceImpl.buy(dto, token);
		
		if(transaction == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(transaction);
	}
	
	@PostMapping("/sell")
	public ResponseEntity<Transaction> doSell(@RequestBody PortfolioDTO dto, BindingResult bindingResult, @RequestHeader Map<String, String> header){
		
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		String token = header.get("authorization");
		
		Transaction transaction = transactionServiceImpl.sell(dto, token);
		
		if(transaction == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(transaction);
	}
	
}
