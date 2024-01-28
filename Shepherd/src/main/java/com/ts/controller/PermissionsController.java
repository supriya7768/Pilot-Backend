package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.Permissions;
import com.ts.service.PermissionsService;

@CrossOrigin("*")
@RestController
public class PermissionsController {

	@Autowired
	PermissionsService ps;

	@PostMapping("/addPermission")
	public ResponseEntity<String> addPermission(@RequestBody Permissions permission, Authentication authentication) {
		return ps.addPermission(permission, authentication);
	}

	@GetMapping("/getAllPermissions")
	@ResponseBody
	public List<Permissions> getAllPermissions() {
		List<Permissions> allPermissions = ps.getAllPermissions();
		return allPermissions;
	}

}
