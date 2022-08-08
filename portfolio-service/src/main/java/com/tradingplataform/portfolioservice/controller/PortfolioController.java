package com.tradingplataform.portfolioservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tradingplataform.portfolioservice.model.Portfolio;
import com.tradingplataform.portfolioservice.service.impl.PortfolioService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

	@Autowired
	PortfolioService portfolioService;
	
	@GetMapping
	public ResponseEntity<List<Portfolio>> getAll(@RequestHeader Map<String, String> header){
		List<Portfolio> portfolios = portfolioService.findAll();
		if (portfolios.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(portfolios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Portfolio> getById(@PathVariable("id") int id) {
		Optional<Portfolio> portfolio = portfolioService.find(id);
		if (portfolio.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
			
		return ResponseEntity.ok(portfolio.get());
	}
	
	@GetMapping("/byuser/{userId}")
    public ResponseEntity <List<Portfolio>> getByUserId(@PathVariable("userId") int userId) {
        List<Portfolio> portfolio = portfolioService.findAllByUserId(userId);
        if(portfolio.isEmpty())
           return ResponseEntity.noContent().build();
        return ResponseEntity.ok(portfolio);
    }
	
	@PostMapping()
	public ResponseEntity<Portfolio> save(@RequestBody Portfolio portfolio) {
		return ResponseEntity.ok(portfolioService.save(portfolio));
	}
}
