package com.tradingplataform.portfolioservice.service;


import java.util.List;
import java.util.Optional;

import com.tradingplataform.portfolioservice.model.Portfolio;

public interface IPortfolioService {

	public List<Portfolio> findAll();
	public Optional<Portfolio> find(Integer id);
	public Portfolio save(Portfolio portfolio);
	public void delete(int id);
	public List<Portfolio> findAllByUserId(int userID);
	
}
