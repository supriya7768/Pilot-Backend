package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ts.dao.PermissionsRepository;
import com.ts.model.Permissions;

@Service
public class PermissionsService {

	@Autowired
	PermissionsRepository pr;

	public ResponseEntity<String> addPermission(Permissions permission) {
		if (pr.findByPermissionType(permission.getPermissionType()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Permission already exists");
		} else {
			pr.save(permission);
			return ResponseEntity.ok("Adding Permission: " + permission.getPermissionType());
		}
	}

	public List<Permissions> getAllPermissions() {
		return pr.findAll();
	}

}
