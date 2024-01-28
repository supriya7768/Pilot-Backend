package com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.dao.PermissionsRepository;
import com.ts.dao.Role_PermissionsRepository;
import com.ts.dao.RolesRepository;
import com.ts.model.Permissions;
import com.ts.model.Role_Permissions;
import com.ts.model.Roles;

@Service
public class Roles_PermissionsService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PermissionsRepository prepo;

	@Autowired
	private Role_PermissionsRepository rolePermissionsRepository;

	@Autowired
	private PermissionsService permissionsService;

	@Transactional
	public ResponseEntity<String> linkRoleToPermissions(Long roleId, Long permissionId) {

		// Check if the role and permission are available
		if (!rolesRepository.existsById(roleId)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role");
		}
		if (!prepo.existsById(permissionId)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid permission");
		}

		// Find the role and permission by their IDs
		Roles role = rolesRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("Role not found"));
		Permissions permission = permissionsService.findById(permissionId)
				.orElseThrow(() -> new IllegalArgumentException("Permission not found"));

		// Check if the association already exists
		boolean isAlreadyLinked = rolePermissionsRepository.existsByRoleAndPermission(role, permission);
		if (isAlreadyLinked) {
			return ResponseEntity.badRequest().body("Role is already linked to the permission.");
		}

		// Create a new Role_Permissions entity and link the role and permission
		Role_Permissions rolePermissions = new Role_Permissions();
		rolePermissions.setRole(role);
		rolePermissions.setPermission(permission);

		// Save the Role_Permissions entity
		rolePermissionsRepository.save(rolePermissions);

		return ResponseEntity.ok("Role linked to permission successfully.");
	}
}
