package com.luka.lukauth.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDTO {
	
	private int id;
	private String username;
	private String email;
	private List<RoleReadDTO> roles;
	
}
