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

import com.ts.model.Roles;
import com.ts.service.RolesService;

@CrossOrigin("*")
@RestController
public class RolesController {

    @Autowired
    RolesService rs;

    @PostMapping("/addRole")
    public ResponseEntity<String> addRole(@RequestBody Roles role, Authentication authentication) {
        return rs.addRole(role, authentication);
    }

    @GetMapping("/getAllRoles")
    @ResponseBody
    public List<Roles> getAllRoles() {
        List<Roles> allRoles = rs.getAllRoles();
        return allRoles;
    }

}
