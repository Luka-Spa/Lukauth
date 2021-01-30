package com.luka.lukauth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.luka.lukauth.dtos.UserReadDTO;
import com.luka.lukauth.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/admin")
public class UserController {

	@Autowired
	UserService userService;
	
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserReadDTO>> getAll(){
		return ResponseEntity.ok(userService.getAll());
	}
	
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserReadDTO> getById(@PathVariable int id) {
		UserReadDTO user = userService.getById(id);
		if(user == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
		return ResponseEntity.ok(user);
	}
	
}
