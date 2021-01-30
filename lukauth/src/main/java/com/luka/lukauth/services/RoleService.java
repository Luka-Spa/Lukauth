package com.luka.lukauth.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luka.lukauth.dtos.RoleCreateDTO;
import com.luka.lukauth.dtos.RoleReadDTO;
import com.luka.lukauth.models.RoleEntity;
import com.luka.lukauth.models.UserEntity;
import com.luka.lukauth.repositories.IRoleRepository;
import com.luka.lukauth.repositories.IUserRepository;

@Service
public class RoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public boolean add(RoleCreateDTO role) {
		roleRepository.save(mapper.map(role, RoleEntity.class));
		return true;
	}

	public List<RoleReadDTO> getAll() {
		return mapper.map(roleRepository.findAll(), new TypeToken<List<RoleReadDTO>>() {}.getType());
	}

	public RoleReadDTO getById(int id) {
		Optional<RoleEntity> role = roleRepository.findById(id);
		if(role.isPresent())
			return mapper.map(role.get(), RoleReadDTO.class);
		return null;
	}

	public boolean delete(int id) {
		Optional<RoleEntity> role = roleRepository.findById(id);
		if(role.isPresent()) {
			List<UserEntity> users = userRepository.findAll();
			for (int i = 0; i < users.size(); i++) {
			  if(users.get(i).getRoles().contains(role.get())) {
			  	users.get(i).getRoles().remove(role.get());
			  }
			}
			userRepository.saveAll(users);
			roleRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
