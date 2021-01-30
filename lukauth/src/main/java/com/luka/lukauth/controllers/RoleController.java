package com.luka.lukauth.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.luka.lukauth.dtos.RoleCreateDTO;
import com.luka.lukauth.dtos.RoleReadDTO;
import com.luka.lukauth.services.RoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;

@RestController
@RequestMapping("/admin")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<List<RoleReadDTO>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAll());
	}
	
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<RoleReadDTO> getById(@PathVariable int id) throws NotFoundException{
		RoleReadDTO role = roleService.getById(id);
		if(role == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
		return ResponseEntity.ok(role);
	}
	
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<String> createRole(@Valid @RequestBody RoleCreateDTO role){
		roleService.add(role);
		return ResponseEntity.ok("");
	}
	
	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRole(@PathVariable int id){
		if(!roleService.delete(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
		return ResponseEntity.ok("");
	}
	
	
}
