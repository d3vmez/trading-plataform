package com.tradingplataform.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tradingplataform.models.User;
import com.tradingplataform.repository.UserRepository;

class AuthUserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	List<User> listUser;
	Optional<User> user3;
	User user4;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		listUser = new ArrayList<>();
		
		// Cargar lista
		User user1 = new User();
		User user2 = new User();
		
		listUser.add(user1);
		listUser.add(user2);
		
		// Cargar opcional
		user3 = Optional.of(new User("email","clave"));
		
		// Cargar user
		user4 = new User("pablo","123");
	}

	@Test
	void testFindAll() {
		when(userRepository.findAll()).thenReturn(listUser);
		assertEquals(2, userService.findAll().size());
	}

	@Test
	void testFindByEmail() {
		when(userRepository.findByEmail("marcos@marcos.com")).thenReturn(user3);
		assertEquals(true, userService.findByEmail("marcos@marcos.com").isPresent());
	}

	@Test
	void testFind() {
		when(userRepository.findById(1)).thenReturn(user3);
		assertEquals(true, userService.find(1).isPresent());
	}

	@Test
	void testSave() {
		when(userRepository.save(any(User.class))).thenReturn(user4);
		assertNotNull(userService.save(new User()));		
	}

	@Test
	void testDelete() {
		doThrow(new PersistenceException("Exception occured")).when(userRepository).deleteById(2);
		assertDoesNotThrow(()->userService.delete(1));
	}

}
