package com.example.ad.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.ad.domain.User;
import com.example.ad.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService uservice;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = uservice.findUserByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		
		return new MyUserDetails(user);
	}

}
