package com.tradingplataform.prueba.service;

import java.util.List;
import java.util.Optional;

import com.tradingplataform.prueba.model.Product;


public interface IProductService {
	
	public List<Product> findAll();
	public Optional<Product> find(Integer id);
	public Product save(Product Product, String userToken);
	public void delete(Integer id);
	public List<Product> findByUserId(int userID);

}
