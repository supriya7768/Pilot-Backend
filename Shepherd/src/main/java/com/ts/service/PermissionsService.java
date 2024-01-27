package com.ts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ts.dao.PermissionsRepository;
import com.ts.model.Permissions;
import com.ts.security.OurUserInfoDetails;

@Service
public class PermissionsService {

	@Autowired
	PermissionsRepository pr;

	@Autowired
	OurUsersService ourUsersService;

	public ResponseEntity<String> addPermission(@RequestBody Permissions permission, Authentication authentication) {
		// Retrieve user details based on the provided email
		OurUserInfoDetails userDetails = (OurUserInfoDetails) authentication.getPrincipal();

		// Check if user exists and their role is SUPERADMIN
		if (userDetails != null && userDetails.hasSuperAdminRole()) {
			if (pr.findByPermissionType(permission.getPermissionType()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Permission already exists");
			} else {
				pr.save(permission);
				return ResponseEntity.ok("Adding Permission: " + permission.getPermissionType());
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to do it");
		}
	}

	public List<Permissions> getAllPermissions() {
		return pr.findAll();
	}

	public Optional<Permissions> findById(Long permissionId) {
		return pr.findById(permissionId);
	}

	
}
