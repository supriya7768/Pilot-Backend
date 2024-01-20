package com.ts;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Override
	public void configure(HttpSecurity http) throws Exception {
//		authorizeRequests().antMatchers("/role-form").hasRole("superadmin").anyRequest().authenticated().and()
//				.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // Implement your UserDetailsService or use an existing one
//        return new YourUserDetailsService();
//    }
}
