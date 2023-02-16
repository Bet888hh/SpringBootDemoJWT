package com.Betto.ProgettoSecurity.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Betto.ProgettoSecurity.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto GetUserByUserId(String userId);
}
