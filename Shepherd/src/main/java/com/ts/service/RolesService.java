package com.ts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ts.dao.RolesRepository;
import com.ts.model.Roles;
import com.ts.security.OurUserInfoDetails;

@Service
public class RolesService {

	@Autowired
	RolesRepository rr;

	public ResponseEntity<String> addRole(Roles role, Authentication authentication) {
		// Retrieve user details based on the provided email
		OurUserInfoDetails userDetails = (OurUserInfoDetails) authentication.getPrincipal();

		// Check if user exists and has the required authority
		if (userDetails != null) {
			if (rr.findByRoleType(role.getRoleType()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Role already exists");
			} else {
				role.setRoleType(role.getRoleType().toUpperCase());
				rr.save(role);
				return ResponseEntity.ok("Adding Role: " + role.getRoleType());
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not authorized to do so");
		}
	}

	public List<Roles> getAllRoles() {
		return rr.findAll();
	}

	public ResponseEntity<String> updateRole(String roleType, Roles role, Authentication authentication) {
		OurUserInfoDetails userDetails = (OurUserInfoDetails) authentication.getPrincipal();
		// Check if user has SUPERADMIN authority
		if (userDetails != null) {
			// Retrieve existing role by role type
			Optional<Roles> optionalRole = rr.findByRoleType(roleType);
			if (optionalRole.isPresent()) {
				Roles existingRole = optionalRole.get();
				// Check if the existing role type is SUPERADMIN
				if (existingRole.getRoleType().equals("SUPERADMIN")) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							.body("You are not permitted to update SUPERADMIN role");
				}
				// Update role type if not SUPERADMIN
				existingRole.setRoleType(role.getRoleType().toUpperCase());
				rr.save(existingRole);
				return ResponseEntity.ok("Role updated successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update roles");
		}
	}

	public ResponseEntity<String> deleteRole(String roleType, Authentication authentication) {
		OurUserInfoDetails userDetails = (OurUserInfoDetails) authentication.getPrincipal();
		// Check if user has SUPERADMIN authority
		if (userDetails != null && userDetails.hasRole("SUPERADMIN")) {
			// Retrieve existing role by role type
			Optional<Roles> optionalRole = rr.findByRoleType(roleType);
			if (optionalRole.isPresent()) {
				Roles existingRole = optionalRole.get();
				// Check if the existing role type is SUPERADMIN
				if (existingRole.getRoleType().equals("SUPERADMIN")) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							.body("You are not permitted to delete SUPERADMIN role");
				}
				// Delete the role
				rr.delete(existingRole);
				return ResponseEntity.ok("Role deleted successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to delete roles");
		}
	}
}
