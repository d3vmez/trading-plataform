package com.tradingplataform.authservice.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	// TODO Aregar colección para los roles
	
	public SecurityUser(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	/**
	 * 
	 * Método para transformar la entidad de usuario en la base de datos en un
	 * objeto ususario que contenga los privilegios
	 * 
	 * @param user
	 * @return
	 */
	public static SecurityUser build (User user) {
		
		// TODO transformar roles en authorities
		
		return new SecurityUser(user.getEmail(), user.getPassword());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
