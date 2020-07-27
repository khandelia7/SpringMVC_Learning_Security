package com.demo.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Model.User;
import com.demo.repository.UserRepository;

@Service
public class AuthServices implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// go to DB and fetch user based on username
		System.out.println(username);
		// User user = db.loadUserByUsername(username);
		User user = userRepository.findByUsername(username); //This method is in UserRepository
		if (user == null) {
			throw new UsernameNotFoundException("Invalid");
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getAuthorities());
	}
}
