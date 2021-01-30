package com.luka.lukauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.lukauth.models.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer>{

}
