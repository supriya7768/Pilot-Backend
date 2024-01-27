package com.ts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.OurUsers;
import com.ts.service.OurUsersService;

@CrossOrigin("*")
@RestController
public class OurUsersController {

	@Autowired
	OurUsersService ourUsersService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String goHome() {
		return "This is publicly not accesible & needing authentication ";
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<Object> saveUSer(@RequestBody OurUsers ourUser) {
		ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
		OurUsers result = ourUsersService.saveUser(ourUser);
		if (result.getId() > 0) {
			return ResponseEntity.ok("User is saved");
		}
		return ResponseEntity.status(404).body("Error, User not saved");
	}

	@GetMapping("/users/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Object> getAllUSers() {
		return ResponseEntity.ok(ourUsersService.findAllUsers());
	}

	@GetMapping("/users/single")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Object> getMyDetails() {
		return ResponseEntity.ok(ourUsersService.findByEmail(getLoggedInUserDetails().getUsername()));
	}

	public UserDetails getLoggedInUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			return (UserDetails) authentication.getPrincipal();
		}
		return null;
	}
}
