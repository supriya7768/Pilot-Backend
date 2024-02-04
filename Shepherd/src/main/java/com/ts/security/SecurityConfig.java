package com.ts.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ts.dao.OurUsersRepository;
import com.ts.dao.RolesRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final OurUsersRepository ourUsersRepository;
	private final RolesRepository rolesRepository;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(OurUsersRepository ourUsersRepository, RolesRepository rolesRepository,
			PasswordEncoder passwordEncoder) {
		this.ourUsersRepository = ourUsersRepository;
		this.passwordEncoder = passwordEncoder;
		this.rolesRepository = rolesRepository;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/user/save", "/product/all").permitAll()
				.anyRequest().authenticated()).httpBasic(withDefaults()).formLogin(withDefaults())
				.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new OurUserInfoUserDetailsService(ourUsersRepository, rolesRepository, passwordEncoder);
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}
}
