package com.ts.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.service.Roles_PermissionsService;

@RestController
public class Role_PermissionsController {

    @Autowired
    private Roles_PermissionsService rolePermissionsService;

    @PostMapping("/linkRoleToPermissions")
    public ResponseEntity<String> linkRoleToPermissions(@RequestBody Map<String, Long> requestBody) {
        Long roleId = requestBody.get("roleId");
        Long permissionId = requestBody.get("permissionId");
        
        ResponseEntity<String> response = rolePermissionsService.linkRoleToPermissions(roleId, permissionId);
        
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Role linked to permission successfully.");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }
}
