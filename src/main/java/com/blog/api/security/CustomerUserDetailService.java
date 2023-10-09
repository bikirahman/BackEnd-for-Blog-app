package com.blog.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.api.entity.User;
import com.blog.api.exception.ResourceNotFoundException;
import com.blog.api.repositories.UserRepo;
@Service
public class CustomerUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByName(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username : " + username, 0));
		return user;
	}

	@Override
	public String toString() {
		return "CustomerUserDetailService [userRepo=" + userRepo + "]";
	}
}