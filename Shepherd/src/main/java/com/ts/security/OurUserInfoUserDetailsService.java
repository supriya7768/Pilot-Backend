package com.ts.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ts.dao.OurUsersRepository;
import com.ts.model.OurUsers;

@Configuration
public class OurUserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private OurUsersRepository ourUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<OurUsers> user = ourUserRepository.findByEmail(username);
		return user.map(OurUserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist"));
	}
}
