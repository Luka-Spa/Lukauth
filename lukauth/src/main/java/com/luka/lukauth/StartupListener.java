package com.luka.lukauth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.luka.lukauth.dtos.RegisterCreateDTO;
import com.luka.lukauth.dtos.RoleCreateDTO;
import com.luka.lukauth.dtos.UserUpdateDTO;
import com.luka.lukauth.services.AuthenticationService;
import com.luka.lukauth.services.RoleService;
import com.luka.lukauth.services.UserService;

@Component
public class StartupListener implements 
  ApplicationListener<ContextRefreshedEvent> {

		@Value("${auth.admin_username}")
		private String username;
		
		@Value("${auth.admin_password}")
		private String password;
		
		@Autowired
		private AuthenticationService authenticationService;
		
		@Autowired
		private RoleService roleService;
		
		@Autowired
		private UserService userService;
		
    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
    	authenticationService.saveUser(new RegisterCreateDTO(username,password,"luka@gmail.com"));
    	roleService.add(new RoleCreateDTO("ROLE_ADMIN"));
    	userService.update(new UserUpdateDTO(1, username,password,"luka@gmail.com", List.of(3)));
 
    }
}