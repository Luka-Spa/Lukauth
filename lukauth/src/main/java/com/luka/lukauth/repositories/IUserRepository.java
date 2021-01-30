package com.luka.lukauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.lukauth.models.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

		UserEntity getByUsername(String username);
}
