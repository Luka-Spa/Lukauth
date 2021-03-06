package com.luka.lukauth.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCreateDTO {
	
	@NotBlank()
	private String username;
	
	@Size(min = 8 , max = 24)
	@NotBlank()
	private String password;
	
	@NotBlank()
	@Email()
	private String email;
	
}
