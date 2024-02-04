package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.Roles;
import com.ts.service.RolesService;

@CrossOrigin("*")
@RestController
public class RolesController {

    @Autowired
    RolesService rs;

    @PostMapping("/addRole")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<String> addRole(@RequestBody Roles role, Authentication authentication) {
        return rs.addRole(role, authentication);
    }

    
    @GetMapping("/getAllRoles")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public List<Roles> getAllRoles() {
        List<Roles> allRoles = rs.getAllRoles();
        return allRoles;
    }
    
    @PutMapping("/updateRole/{roleType}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<String> updateRole(@PathVariable String roleType, @RequestBody Roles role, Authentication authentication) {
        return rs.updateRole(roleType, role, authentication);
    }
    
    @DeleteMapping("/deleteRole/{roleType}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<String> deleteRole(@PathVariable String roleType, Authentication authentication) {
        return rs.deleteRole(roleType, authentication);
    }

}
