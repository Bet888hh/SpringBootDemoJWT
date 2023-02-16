package com.Betto.ProgettoSecurity.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import lombok.extern.java.Log;


public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		String header = request.getHeader(SecurityConstant.HEADER_STRING ); // Authorization 
		//C'è authorization?
		if(header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		//funzione che controlla il JWT 
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstant.HEADER_STRING);
		
		System.err.println(token);
		
		
 		if(token!=null) {
			
			token=token.replace(SecurityConstant.TOKEN_PREFIX, "");
			// parsiamo il token in modo da verificare che l'utente sia autorizzato o che il token non sia scaduto
			String user= Jwts.parser()
					.setSigningKey(SecurityConstant.getTokenSecret())
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null , new ArrayList<>()); 
			}
		}
		return null;
	}

	

	
	
	
	
	
	
}
