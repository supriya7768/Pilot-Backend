package com.ts.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Object> saveUser(@RequestBody OurUsers ourUser) {
		// Check if any user detail is null
		if (ourUser.getUserName() == null || ourUser.getEmail() == null || ourUser.getPassword() == null
				|| ourUser.getRoles() == null) {
			return ResponseEntity.badRequest().body("Fill all details");
		}

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		if (!Pattern.matches(emailRegex, ourUser.getEmail())) {
			return ResponseEntity.badRequest().body("Invalid email format");
		}

		String username = ourUser.getUserName();
		if (username.length() > 1) {
			String formattedUsername = Character.toUpperCase(username.charAt(0)) + username.substring(1).toLowerCase();
			ourUser.setUserName(formattedUsername);
		} else {
			ourUser.setUserName(username.toUpperCase());
		}

		ourUser.setRoles(ourUser.getRoles().toUpperCase());

		// Check if SUPERADMIN already exists in roles
		if (ourUser.getRoles().contains("SUPERADMIN") && ourUsersService.isSuperAdminPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Only one SUPERADMIN can be present");
		}

		ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));

		OurUsers savedUser = ourUsersService.saveUser(ourUser);
		if (savedUser != null) {
			return ResponseEntity.ok("User is saved");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exists with this email.");
		}
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
