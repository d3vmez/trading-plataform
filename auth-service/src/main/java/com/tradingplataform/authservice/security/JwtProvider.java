package com.tradingplataform.authservice.security;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

/**
 * 
 * Clase para generar un token, comprobar si es válido, está expirado...
 * 
 * @author mgomezgarrote
 *
 */
@Component
public class JwtProvider {

	private final static Logger log = Logger.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret_key;

	@Value("${jwt.expiration}")
	private int expiration_time;

	private SecretKey secretKey;

	/**
	 * 
	 * Método para convertir una String key a Java Key
	 * 
	 * @return Java Key instancia
	 */
	@PostConstruct
	protected void init() {

		byte[] keyBytes = Decoders.BASE64.decode(this.secret_key);
		this.setSecretKey(Keys.hmacShaKeyFor(keyBytes));
	}

	public String createToken(String email) {

		String token = Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration_time)).signWith(secretKey).compact();

		return token;
	}

	public String getEmailFromToken(String token) {

		try {
			String email = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
					.getSubject();
			return email;
		} catch (Exception e) {
			return "bad token";
		}

	}

	public boolean isValidateToken(String token) {

		try {

			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;

		} catch (MalformedJwtException e) {

			log.error("Malformed Jwt");

		} catch (UnsupportedJwtException e) {

			log.error("Unsupported Jwt");

		} catch (ExpiredJwtException e) {

			log.error("Expired Jwt");

		} catch (IllegalArgumentException e) {

			log.error("Empty Jwt");

		} catch (SignatureException e) {

			log.error("Fail Jwt signature");

		}

		return false;

	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}
}
