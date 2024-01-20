package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.Roles;
import com.ts.service.RolesService;

@CrossOrigin("*")
@RestController
public class RolesController {

	@Autowired
	RolesService rs;

	@PostMapping("/addRole")
	public ResponseEntity<String> addRole(@RequestBody Roles role) {
		return rs.addRole(role);
	}

	@GetMapping("/getAllRoles")
	@ResponseBody
	public List<Roles> getAllRoles() {
		List<Roles> allRoles = rs.getAllRoles();
		return allRoles;
	}

	// --Theamlyf code--

	@PostMapping("/assign-roles")
	public String assignRoles(@RequestParam("adminRole") String adminRole,
			@RequestParam("salesRole") String salesRole) {
		// Process role assignment logic here
		// You can save the assigned roles in the database or perform any necessary
		// operations

		return "redirect:/role-form";
	}

	@GetMapping("/role-form")
	public String showRoleForm(Model model) {
		return "role-form";
	}

}
