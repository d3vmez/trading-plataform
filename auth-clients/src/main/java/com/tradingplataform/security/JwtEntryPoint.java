package com.tradingplataform.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * Clase para buscar si existe un token válido, si no devolverá
 * una respuesta 401 (No privilegios)
 * 
 * @author mgomezgarrote
 *
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	
	private final static Logger log = Logger.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		log.error("fail in commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized User");
		
	}

}
