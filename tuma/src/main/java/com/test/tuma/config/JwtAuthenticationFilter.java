package com.test.tuma.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.test.tuma.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Autowired
	private final JwtService jwtService;

	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {

		/** Check if we have jwt **/

		final String authHeader = request.getHeader("Authorization");
		logger.info("authHeader --> {}",authHeader);
		final String jwt;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			return;
		}
		/** Extract jwt header **/
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUserName(jwt);
		logger.info("userEmail ,SecurityContextHolder.getContext().getAuthentication() -->{} {}",userEmail,SecurityContextHolder.getContext().getAuthentication());

		// SecurityContextHolder is used to check if the user is Authenticated
		// if the Authentication is null = the user is not authenticated/not connected
		if ((userEmail != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
			// Get user details from database
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			ObjectMapper mapper =new ObjectMapper();
			logger.info(" jwtService.isTokenValid(jwt, userDetails),jwt  -->{} {}",jwtService.isTokenValid(jwt, userDetails),jwt);
			// check if token is still valid

			if (jwtService.isTokenValid(jwt, userDetails)==false) {
				logger.info("isTokenValid ----",jwt);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				logger.info("authToken -->" , authToken);
				// updating security context holder
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}

}
