package com.tradingplataform.authservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tradingplataform.authservice.models.User;
import com.tradingplataform.authservice.repository.UserRepository;
import com.tradingplataform.authservice.service.impl.UserService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {UserServiceTest.class})
class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	List<User> users;
	User user;

	@BeforeEach
	void setUp() throws Exception {
		
		users = new ArrayList<>();
		users.add(new User("marcos@email.com", "123"));
		users.add(new User("pablos@email.com", "123"));
		
	}

	@Test
	@Order(1)
	public void testFindAll() {
		
		when(userRepository.findAll()).thenReturn(users);
		assertEquals(2,userService.findAll().size());
		
	}

	@Test
	@Order(2)
	void testFindByEmail() {
		
		when(userRepository.findByEmail("marcos@email.com")).thenReturn(Optional.of(users.get(0)));
		assertEquals(users.get(0), userService.findByEmail("marcos@email.com").get());
		
	}

	@Test
	@Order(3)
	void testFind() {
		when(userRepository.findById(1)).thenReturn(Optional.of(users.get(1)));
		assertEquals(users.get(1), userService.find(1).get());
	}

	@Test
	@Order(4)
	void testSave() {
		when(userRepository.save(users.get(0))).thenReturn(users.get(0));
		assertEquals(users.get(0), userService.save(users.get(0)));
	}

	@Test
	@Order(5)
	void testDelete() {
		userService.delete(1);
		verify(userRepository, times(1)).deleteById(1);
	}


}
