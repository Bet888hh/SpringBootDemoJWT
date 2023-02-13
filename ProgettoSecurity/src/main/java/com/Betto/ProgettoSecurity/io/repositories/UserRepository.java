package com.Betto.ProgettoSecurity.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Betto.ProgettoSecurity.io.entity.UserEntity;



@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String UserId);
	UserEntity findByLastName(String LastName);
	UserEntity findByFirstName(String FirstName);
}

