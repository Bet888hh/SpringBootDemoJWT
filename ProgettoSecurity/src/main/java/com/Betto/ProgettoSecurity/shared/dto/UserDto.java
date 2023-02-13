package com.Betto.ProgettoSecurity.shared.dto;

import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1943039827608604806L;
	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;

	
	private String password;
	private String ePassword;
	private String emailVerificationStatus;

}
