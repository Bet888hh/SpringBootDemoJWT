package com.Betto.ProgettoSecurity.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Betto.ProgettoSecurity.services.UserService;

import lombok.AllArgsConstructor;




@SuppressWarnings("deprecation")
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

private final BCryptPasswordEncoder bCryptPasswordEncoder;
private UserService userDetailService;






@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
}



@Override
protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeHttpRequests()
	.antMatchers(HttpMethod.POST,SecurityConstant.SING_UP_URL)
	.permitAll()
	.anyRequest().authenticated().and()
	.addFilter(getAuthenticationFilter())
	.addFilter(new AuthorizationFilter(authenticationManager()))
	// STATELESS API
	.sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	
}





public AuthenticationFilter getAuthenticationFilter() throws Exception {
	final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	filter.setFilterProcessesUrl("/users/login");
	return filter;
	
}





}
