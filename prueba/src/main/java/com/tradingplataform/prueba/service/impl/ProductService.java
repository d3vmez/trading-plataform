package com.tradingplataform.prueba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradingplataform.prueba.feignclient.UserFeignClient;
import com.tradingplataform.prueba.model.Product;
import com.tradingplataform.prueba.repository.ProductRepository;
import com.tradingplataform.prueba.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> find(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product product, String token) {
		
		Integer userId = userFeignClient.getId(token);
		if(userId != null) {
			product.setUserId(userId);
		}
		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findByUserId(int userID) {
		return productRepository.findByUserId(userID);
	}

}
