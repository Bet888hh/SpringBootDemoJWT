 package com.Betto.ProgettoSecurity.services.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Betto.ProgettoSecurity.io.entity.UserEntity;
import com.Betto.ProgettoSecurity.io.repositories.UserRepository;
import com.Betto.ProgettoSecurity.services.UserService;
import com.Betto.ProgettoSecurity.shared.dto.UserDto;
import com.Betto.ProgettoSecurity.shared.Utils;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	
	@Autowired
	 BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("esiste già!");
		}

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		
		// random 30 characther id 
		userEntity.setEPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userEntity.setUserId(utils.generateUserId(30));

		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity==null)throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEPassword(),new ArrayList<>() );
	}
 
	
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(userEntity, returnValue);
		
		
		return returnValue;
	}

	@Override
	public UserDto GetUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		BeanUtils.copyProperties(userEntity, returnValue);
		
		
		return returnValue;
	}

}
