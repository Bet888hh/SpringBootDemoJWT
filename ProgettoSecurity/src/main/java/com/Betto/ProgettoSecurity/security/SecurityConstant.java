package com.Betto.ProgettoSecurity.security;

import com.Betto.ProgettoSecurity.SpringApplicationContext;

public class SecurityConstant {
	//864000000
	public static final long EXPIRATION_TIME =90000 ; // novantasecondi
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String SING_UP_URL="/users";
	
	
	
	public static String  getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBeans("appProperties");
		return appProperties.getTokenSecret();
	}
	

}
