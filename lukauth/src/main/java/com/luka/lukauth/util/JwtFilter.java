package com.luka.lukauth.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luka.lukauth.dtos.MainUserDetails;
import com.luka.lukauth.services.AuthenticationService;

import io.jsonwebtoken.JwtException;


@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtUtil jwtUtil;

	@Value("${jwt.prefix}")
	private String tokenPrefix;
	
	protected String extractJwtFromHeader(String authorizationHeader) {
		if(authorizationHeader.startsWith(tokenPrefix))
			return authorizationHeader.replace(tokenPrefix,"");
		else 
			return authorizationHeader;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = null;
		String username = null;

		if (authorizationHeader != null) {
				jwt = extractJwtFromHeader(authorizationHeader);
				try {
				username = jwtUtil.extractUsername(jwt);
				}
				catch(JwtException ex) {
					logger.error("jwt expired");
				}


			if (username != null) {
				MainUserDetails userDetails = authenticationService.loadUserByUsername(username);
				if (jwtUtil.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);

	}

}
