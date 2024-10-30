package com.test.tuma.service;

import java.security.Key;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.tuma.config.JwtAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
	
	@Value("${jwt_validity}") // Default to 1 hour if not set
	private long validityInMilliseconds;
	
	private static final String SECRET_KEY="5ab7e915a5db6ddd1e908a7728327636f89459ee38df89eb9b9f0ca56d55368b";
	public String extractUserName(String token) {
	
		logger.info("token inside extractUserName ",token);
		return extractClaim(token, Claims::getSubject);
	}
	
	//Generate token without having extraclaims but generate form userDetails
	public String generateToken(UserDetails userdetails) {
		return generateToken(new HashMap<>(), userdetails);
	}
	
	//
	public String generateToken(
			Map<String, Object> extraClaims,
			UserDetails userDetails) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();//compact will generate and return the token
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		ObjectMapper objectMapper = new ObjectMapper();
		logger.info("Token",token);
		try {
			logger.info("claims -->{}",objectMapper.writeValueAsString(claims));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return claimsResolver.apply(claims);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		logger.info("isToken Valid toke::",token);
		final String userName = extractUserName(token);
		logger.info("isToken Valid username::",userName);
		return (userName.equals(userDetails.getUsername()) && isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		logger.info("token inside extractAllClaims ",token);
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey() {
		// Decode the key to using base64
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}

}
