package com.Betto.ProgettoSecurity.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Betto.ProgettoSecurity.exceptions.UserServiceException;
import com.Betto.ProgettoSecurity.services.UserService;
import com.Betto.ProgettoSecurity.shared.dto.UserDto;
import com.Betto.ProgettoSecurity.ui.model.request.UserDetailsRequestModel;
import com.Betto.ProgettoSecurity.ui.model.response.ErrorMessages;
import com.Betto.ProgettoSecurity.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping(
    path = "/{id}",
    produces = { MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
  )
  public UserRest getUser(@PathVariable String id) {
    UserRest returnValue = new UserRest();

    UserDto userDto = userService.GetUserByUserId(id);
    BeanUtils.copyProperties(userDto, returnValue);

    return returnValue;
  }

  @GetMapping
  public UserRest getUsers() {
    return null;
  }

  @PostMapping(
    consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
    produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
  )
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
    UserRest returnValue = new UserRest();
    
    if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    
    UserDto userDto = new UserDto();
    

    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);

    BeanUtils.copyProperties(createdUser, returnValue);

    return returnValue;
  }

  @DeleteMapping
  public String deleteUser() {
    return "un utente eliminato  ";
  }

  @PutMapping
  public String updateUser() {
    return "un utente aggiornato ";
  }
}
