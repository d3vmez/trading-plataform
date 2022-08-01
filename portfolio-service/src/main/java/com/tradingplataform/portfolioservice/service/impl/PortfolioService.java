package com.tradingplataform.portfolioservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.portfolioservice.model.Portfolio;
import com.tradingplataform.portfolioservice.repository.PortfolioRepository;
import com.tradingplataform.portfolioservice.service.IPortfolioService;

@Service
public class PortfolioService implements IPortfolioService{

	@Autowired
	PortfolioRepository portfolioRepository;
	
	@Override
	public List<Portfolio> findAll() {
		return portfolioRepository.findAll();
	}

	@Override
	public Optional<Portfolio> find(Integer id) {
		return portfolioRepository.findById(id);
	}

	@Override
	public Portfolio save(Portfolio portfolio) {
		return portfolioRepository.save(portfolio);
	}

	@Override
	public void delete(int id) {
		portfolioRepository.deleteById(id);	
	}

	@Override
	public List<Portfolio> findAllByUserId(int userID) {
		return portfolioRepository.findAllByUserId(userID);
	}


}
