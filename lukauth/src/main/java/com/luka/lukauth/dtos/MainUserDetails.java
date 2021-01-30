package com.luka.lukauth.dtos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.luka.lukauth.models.RoleEntity;
import com.luka.lukauth.models.UserEntity;

public class MainUserDetails implements UserDetails {

	static final long serialVersionUID = 6004709787659585059L;
	private UserEntity user;
	
	public MainUserDetails(UserEntity user) {
		this.user=user;
	}
	public String getId() {
		return String.valueOf(user.getId());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<RoleEntity> roles = user.getRoles();
		List<SimpleGrantedAuthority> _roles = new ArrayList<SimpleGrantedAuthority>();
		roles.forEach(role -> {
			_roles.add(new SimpleGrantedAuthority(role.getRole_name()));
		});
		return _roles;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
