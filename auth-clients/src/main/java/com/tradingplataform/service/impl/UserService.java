package com.tradingplataform.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tradingplataform.models.User;
import com.tradingplataform.models.resttemplates.Product;
import com.tradingplataform.repository.UserRepository;
import com.tradingplataform.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService{
	
	Logger log = Logger.getLogger(UserService.class);
	
	// Prueba
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Product> findProducts(int userId){
		
		List<Product> products = restTemplate.getForObject("http://localhost:8081//product/byuser/" + userId, List.class);
		return products;	
	}
	/////////////////////////////////////////////////////////////////
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> find(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		log.info("User saved with id: " + user.getId());
		return userRepository.save(user);	
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
		log.info("User deleted with id: " + id);
	}


}
