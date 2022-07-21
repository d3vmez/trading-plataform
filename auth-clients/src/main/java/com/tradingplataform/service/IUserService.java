package com.tradingplataform.service;

import java.util.List;
import java.util.Optional;

import com.tradingplataform.models.User;

public interface IUserService {
	
	public List<User> findAll();
	public Optional<User> findByEmail(String email);
	public Optional<User> find(Integer id);
	public void save(User user);
	public void delete(Integer id);

}
