package com.luka.lukauth.dtos;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;


	@Data
	@AllArgsConstructor
	public class AuthenticationCreateDTO {
		
		@NotBlank()
		private String username;
		@NotBlank()
		private String password;

		
}
