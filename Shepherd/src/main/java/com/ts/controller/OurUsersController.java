package com.ts.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@PreAuthorize("hasAuthority('SUPERADMIN') or hasAuthority('ADMIN')")
	public ResponseEntity<Object> saveUser(@RequestBody OurUsers ourUser) {
		try {
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
				String formattedUsername = Character.toUpperCase(username.charAt(0))
						+ username.substring(1).toLowerCase();
				ourUser.setUserName(formattedUsername);
			} else {
				ourUser.setUserName(username.toUpperCase());
			}

			ourUser.setRoles(ourUser.getRoles().toUpperCase());

			ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));

			// Check if user has authority to add SUPERADMIN
			UserDetails loggedInUserDetails = getLoggedInUserDetails();
			boolean isSuperAdmin = loggedInUserDetails.getAuthorities().stream()
					.anyMatch(role -> role.getAuthority().equals("SUPERADMIN"));

			if (ourUser.getRoles().contains("SUPERADMIN") && isSuperAdmin) {
				OurUsers savedUser = ourUsersService.saveUser(ourUser);
				if (savedUser != null) {
					return ResponseEntity.ok("User is saved");
				} else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("User already exists with this email.");
				}
			}

			// Check if SUPERADMIN already exists in roles
			if (ourUser.getRoles().contains("SUPERADMIN") && ourUsersService.isSuperAdminPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Only one SUPERADMIN can be present");
			}

			OurUsers savedUser = ourUsersService.saveUser(ourUser);
			if (savedUser != null) {
				return ResponseEntity.ok("User is saved");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("User already exists with this email.");
			}
		} catch (AccessDeniedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to do so");
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to do so");
		}
	}

	@GetMapping("/users/getall")
	@PreAuthorize("hasAuthority('SUPERADMIN')")
	public ResponseEntity<List<OurUsers>> getAllUsers() {
		List<OurUsers> allUsers = ourUsersService.findAllUsers();
		return ResponseEntity.ok(allUsers);
	}

//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PutMapping("/users/update")
	@PreAuthorize("hasAuthority('SUPERADMIN')")
	public ResponseEntity<Object> updateUser(@RequestBody OurUsers updatedUser) {
		try {
			// Check if any user detail is null
			if (updatedUser.getUserName() == null || updatedUser.getEmail() == null || updatedUser.getPassword() == null
					|| updatedUser.getRoles() == null) {
				return ResponseEntity.badRequest().body("Fill all details");
			}

			// Get logged-in user details
			UserDetails loggedInUserDetails = getLoggedInUserDetails();

			// Check if the logged-in user has SUPERADMIN role
			boolean isSuperAdmin = loggedInUserDetails.getAuthorities().stream()
					.anyMatch(role -> role.getAuthority().equals("SUPERADMIN"));

			// Check if the logged-in user is SUPERADMIN
			if (!isSuperAdmin) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only SUPERADMIN can edit user details");
			}

			// Check if the new role is not SUPERADMIN
			if (updatedUser.getRoles().equalsIgnoreCase("SUPERADMIN")) {
				return ResponseEntity.badRequest().body("Cannot assign SUPERADMIN role");
			}

			// Check if the user exists
			OurUsers existingUser = ourUsersService.findUserByEmail(updatedUser.getEmail());
			if (existingUser == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}

			// Check if the user being updated is a SUPERADMIN
			if (existingUser.getRoles().equalsIgnoreCase("SUPERADMIN")) {
				return ResponseEntity.badRequest().body("You cannot change the role of the SUPERADMIN user");
			}

			// Update user details
			existingUser.setUserName(updatedUser.getUserName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			existingUser.setRoles(updatedUser.getRoles().toUpperCase());

			// Save the updated user
			OurUsers updatedUserResult = ourUsersService.updateUserDetails(existingUser);

			if (updatedUserResult != null) {
				return ResponseEntity.ok("User details updated successfully");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user details");
			}
		} catch (AccessDeniedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to do so");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	@DeleteMapping("/users/delete/{userId}")
	@PreAuthorize("hasAnyAuthority('SUPERADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {
		boolean deleted = ourUsersService.deleteUserById(userId);
		if (deleted) {
			return ResponseEntity.ok("User deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@GetMapping("/users/single")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Object> getMyDetails() {
		return ResponseEntity.ok(ourUsersService.findByEmail(getLoggedInUserDetails().getUsername()));
	}

	// Utility method to get logged-in user details
	public UserDetails getLoggedInUserDetails() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
