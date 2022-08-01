package com.tradingplataform.portfolioservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradingplataform.portfolioservice.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer>{

	List<Portfolio> findAllByUserId(int userId);
}
