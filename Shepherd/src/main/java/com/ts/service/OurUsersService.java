package com.ts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.dao.OurUsersRepository;
import com.ts.model.OurUsers;

@Service
public class OurUsersService {

	@Autowired
	private OurUsersRepository ourUserRepository;

	public OurUsers saveUser(OurUsers ourUser) {
		// Check if user with the provided email already exists
		Optional<OurUsers> existingUser = ourUserRepository.findByEmail(ourUser.getEmail());
		if (existingUser.isPresent()) {
			return null;
		}

		// Save the user if email is not already present
		return ourUserRepository.save(ourUser);
	}

	public List<OurUsers> findAllUsers() {
		return ourUserRepository.findAll();
	}

	public OurUsers updateUserDetails(OurUsers updatedUser) {
		// Check if the user exists
		Optional<OurUsers> existingUserOptional = ourUserRepository.findByEmail(updatedUser.getEmail());
		if (existingUserOptional.isPresent()) {
			OurUsers existingUser = existingUserOptional.get();

			// Check if the updated role is "SUPERADMIN"
			if (updatedUser.getRoles().contains("SUPERADMIN")) {
				throw new AccessDeniedException("Role 'SUPERADMIN' cannot be assigned during update");
			}

			// Update user details
			existingUser.setUserName(updatedUser.getUserName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword()); // Assuming password is already hashed
			existingUser.setRoles(updatedUser.getRoles());

			// Save and return updated user
			return ourUserRepository.save(existingUser);
		} else {
			// User not found
			return null;
		}
	}

	@Transactional
	public boolean deleteUserById(int userId) {
		OurUsers userToDelete = ourUserRepository.findById(userId).orElse(null);
		if (userToDelete == null) {
			return false; // User not found
		}

		if ("SUPERADMIN".equalsIgnoreCase(userToDelete.getRoles())) {
			return false; // Cannot delete SUPERADMIN
		}

		ourUserRepository.delete(userToDelete);
		return true;
	}

	public Object findByEmail(String email) {
		return ourUserRepository.findByEmail(email);
	}

	public OurUsers findByEmailAndPassword(String email, String password) {
		return ourUserRepository.findByEmailAndPassword(email, password);
	}

	public boolean isSuperAdminPresent() {
		return ourUserRepository.existsByRolesContainingIgnoreCase("SUPERADMIN");
	}

	public OurUsers findUserByEmail(String email) {
		return ourUserRepository.findUserByEmail(email);
	}

}
