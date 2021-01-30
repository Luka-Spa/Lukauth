package com.luka.lukauth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luka.lukauth.dtos.MainUserDetails;
import com.luka.lukauth.dtos.RegisterCreateDTO;
import com.luka.lukauth.models.UserEntity;
import com.luka.lukauth.repositories.IUserRepository;


@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public MainUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.getByUsername(username);
		return new MainUserDetails(user);
	}
	public boolean saveUser(RegisterCreateDTO user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.save(mapper.map(user, UserEntity.class));
		return true;
		
	}
	

	
}
