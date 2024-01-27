package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ts.dao.Role_PermissionsRepository;
import com.ts.dao.RolesRepository;
import com.ts.model.Roles;
import com.ts.security.OurUserInfoDetails;

@Service
public class RolesService {

	@Autowired
	RolesRepository rr;

	@Autowired
	Role_PermissionsRepository rpRepository;

	public ResponseEntity<String> addRole(Roles role, Authentication authentication) {
		// Retrieve user details based on the provided email
		OurUserInfoDetails userDetails = (OurUserInfoDetails) authentication.getPrincipal();

		// Check if user exists and their role is SUPERADMIN
		if (userDetails != null && userDetails.hasSuperAdminRole()) {
			if (rr.findByRoleType(role.getRoleType()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Role already exists");
			} else {
				rr.save(role);
				return ResponseEntity.ok("Adding Role: " + role.getRoleType());
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to do it");
		}
	}

	public List<Roles> getAllRoles() {
		return rr.findAll();
	}

}
