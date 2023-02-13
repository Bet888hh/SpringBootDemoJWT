package com.Betto.ProgettoSecurity.ui.model.request;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginRequestModel {

	private String email;
	private String password;
	
	
}
