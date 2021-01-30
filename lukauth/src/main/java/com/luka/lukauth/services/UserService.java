package com.luka.lukauth.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luka.lukauth.dtos.UserReadDTO;
import com.luka.lukauth.dtos.UserUpdateDTO;
import com.luka.lukauth.models.RoleEntity;
import com.luka.lukauth.models.UserEntity;
import com.luka.lukauth.repositories.IRoleRepository;
import com.luka.lukauth.repositories.IUserRepository;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	IRoleRepository roleRepository;
	public boolean update(UserUpdateDTO user) {
		Optional<UserEntity> userOptional = userRepository.findById(user.getId());
		List<RoleEntity> roles = roleRepository.findAllById(user.getRoles_ids());
		if(userOptional.isPresent()) {
			UserEntity userEntity = userOptional.get();
			userEntity.setEmail(user.getEmail());
			userEntity.setPassword(bcryptEncoder.encode(user.getPassword()));
			userEntity.setRoles((new HashSet<RoleEntity>(roles)));
			userEntity.setUsername(user.getUsername());
			userRepository.save(userEntity);
			return true;
		}
		return false;
	}
	public List<UserReadDTO> getAll() {
		return mapper.map(userRepository.findAll(), new TypeToken<List<UserReadDTO>>() {}.getType());
	}
	
	public UserReadDTO getById(int id) {
		Optional<UserEntity> user = userRepository.findById(id);
		if(user.isPresent())
			return mapper.map(user.get(), UserReadDTO.class);
		return null;
	}
	
}
