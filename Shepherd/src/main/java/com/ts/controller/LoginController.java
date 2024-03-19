package com.ts.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.Login;
import com.ts.model.OurUsers;
import com.ts.service.LoginService;
import com.ts.service.OurUsersService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private OurUsersService ourUsersService;

	@GetMapping("/login/getAll")
	@PreAuthorize("hasAuthority('SUPERADMIN')")
	public ResponseEntity<List<Login>> getAllUsers() {
		List<Login> allUsers = loginService.findAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	@PutMapping("/login/update")
	public ResponseEntity<Object> updateUser(@RequestHeader HttpHeaders headers, @RequestBody Login updatedPassword) {
		try {
			// Extracting username and password from Basic Authentication headers
			String username = getUsernameFromHeaders(headers);
			String password = getPasswordFromHeaders(headers);

			if (username == null || password == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid authentication credentials");
			}

			// Authenticate user based on provided username and password
			Optional<OurUsers> userOptional = ourUsersService.findUserByEmailForLogin(username);
			if (userOptional.isPresent()) {
				OurUsers user = userOptional.get();

				// Compare the provided password with the hashed password stored in the database
				if (!passwordEncoder.matches(password, user.getPassword())) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
				}

				// Check if password and confirmPassword match
				if (!updatedPassword.getPassword().equals(updatedPassword.getConfirmPassword())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Password and confirmPassword do not match");
				}

				// Encrypt the new password before updating
				String encryptedPassword = passwordEncoder.encode(updatedPassword.getPassword());

				// Update password in Login table
				Login login = loginService.findLoginByEmail(username);
				if (login != null) {
					login.setPassword(encryptedPassword);
					loginService.updateLoginDetails(login);

					// Also update password in OurUsers table
					ourUsersService.updatePasswordInOurUsers(username, encryptedPassword);
					return ResponseEntity.ok().body("User password updated successfully.");
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in Login table");
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	// Helper method to extract username from Basic Authentication header
	private String getUsernameFromHeaders(HttpHeaders headers) {
		String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
		if (authorization != null && authorization.startsWith("Basic ")) {
			String base64Credentials = authorization.substring("Basic ".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
			final String[] values = credentials.split(":", 2);
			if (values.length == 2) {
				return values[0];
			}
		}
		return null;
	}

	// Helper method to extract password from Basic Authentication header
	private String getPasswordFromHeaders(HttpHeaders headers) {
		String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
		if (authorization != null && authorization.startsWith("Basic ")) {
			String base64Credentials = authorization.substring("Basic ".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
			final String[] values = credentials.split(":", 2);
			if (values.length == 2) {
				return values[1];
			}
		}
		return null;
	}
}
