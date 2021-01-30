package com.luka.lukauth.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role_name;
 
}