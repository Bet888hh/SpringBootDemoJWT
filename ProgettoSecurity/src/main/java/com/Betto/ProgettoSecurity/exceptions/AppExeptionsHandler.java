package com.Betto.ProgettoSecurity.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.Betto.ProgettoSecurity.ui.model.response.ErrorMessage;



@ControllerAdvice
public class AppExeptionsHandler {
	
	
	//-- ogni volta che lanciamo una UserserviceException
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceExeption(UserServiceException ex,WebRequest req ){
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(),ex.getMessage());
		 
	return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	// altro handler per tutte le altre exception
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherExeptions(Exception ex,WebRequest req ){
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(),ex.getMessage());
		
	return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);	
	}
}

