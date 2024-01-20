package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ts.dao.RolesRepository;
import com.ts.model.Roles;

@Service
public class RolesService {

	@Autowired
	RolesRepository rr;

	public ResponseEntity<String> addRole(Roles role) {
	    if (rr.findByRoleType(role.getRoleType()).isPresent()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Role already exists");
	    } else {
	        rr.save(role);
	        return ResponseEntity.ok("Adding role: " + role.getRoleType());
	    }
	}

	public List<Roles> getAllRoles() {
		return rr.findAll();
	}

}
