package com.Betto.ProgettoSecurity.ui.model.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserRest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
}