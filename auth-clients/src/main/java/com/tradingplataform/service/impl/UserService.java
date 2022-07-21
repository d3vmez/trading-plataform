package com.tradingplataform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tradingplataform.feignclients.ProductFeignClient;
import com.tradingplataform.models.User;
import com.tradingplataform.models.resttemplates_feign.Product;
import com.tradingplataform.repository.UserRepository;
import com.tradingplataform.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService{
	
	Logger log = Logger.getLogger(UserService.class);
	
	// Prueba RestTemplate
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Product> findProducts(int userId){
		
		List<Product> products = restTemplate.getForObject("http://localhost:8081//product/byuser/" + userId, List.class);
		return products;	
	}
	/////////////////////////////////////////////////////////////////
	
	
	// Prueba Feign
	@Autowired
	private ProductFeignClient productFeignClient;
	
	public Product saveProduct(Product product, int userId) {
		// Pasar el id del usuario
		product.setUserId(userId);
		Product productNew = productFeignClient.save(product);
		return productNew;
	}
	
	public Map<String, Object> getProductsWithUser(int userId){
		Map<String, Object> mapProducts = new HashMap<>();
		if(this.find(userId).isEmpty()) {
			mapProducts.put("message", "User doent exists");
			return mapProducts;	
		}
		
		User user = this.find(userId).get();
		List<Product> products = productFeignClient.getProducts(userId);
		
		if(products == null) {
			mapProducts.put("message", "User with id "+ userId +" doesnt have products ");
			return mapProducts;	
		}
		
		mapProducts.put("User", user);
		mapProducts.put("Products", products);
		
		return mapProducts;
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
