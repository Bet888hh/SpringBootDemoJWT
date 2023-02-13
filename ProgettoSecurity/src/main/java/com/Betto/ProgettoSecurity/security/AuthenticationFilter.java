package com.Betto.ProgettoSecurity.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Betto.ProgettoSecurity.SpringApplicationContext;
import com.Betto.ProgettoSecurity.services.UserService;
import com.Betto.ProgettoSecurity.shared.dto.UserDto;
import com.Betto.ProgettoSecurity.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	@Autowired
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
	
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						creds.getEmail(),
						creds.getPassword(),
						new ArrayList<>())
				);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		 
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName = ((User) authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
									.setSubject(userName)
									.setExpiration(new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME))
									.signWith(SignatureAlgorithm.HS512,SecurityConstant.getTokenSecret())
									.compact();
				
		UserService userService = (UserService) SpringApplicationContext.getBeans("userServiceImpl");
			UserDto userDto =userService.getUser(userName);
		response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX+ token);
		response.addHeader("UserID", userDto.getUserId());
	}
	
	
	
	

}
