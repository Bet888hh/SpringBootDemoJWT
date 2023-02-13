package com.Betto.ProgettoSecurity.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7333239005677649766L;
	
	
@Id
@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false,length=50)
	private String firstName;
	@Column(nullable = false,length=50)
	private String lastName;
	@Column(nullable = false,length=120)
	private String email;
	
	@Column(nullable = false)
	private String ePassword;
	
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private Boolean emailVerificationStatus=false;

}

