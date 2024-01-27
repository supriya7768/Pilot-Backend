package com.ts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Object findAllUsers() {
		return ourUserRepository.findAll();
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
}
