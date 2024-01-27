package com.ts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ts.service.PermissionsService;
import com.ts.service.RolesService;
import com.ts.service.Roles_PermissionsService;

@CrossOrigin("*")
@RestController
public class Role_PermissionsController {


    @Autowired
    RolesService rolesService;

    @Autowired
    PermissionsService permissionsService;

    @Autowired
    Roles_PermissionsService rolePermissionsService;

//    @PostMapping("/linkRoleToPermissions")
//    @ResponseBody
//    public ResponseEntity<String> linkRoleToPermissions(@RequestBody Roles role) {
//        // Assuming you have as method to get the permissions for the specified role from the database
//        List<Roles> role = permissionsService.getPermissionsByRole(role);
//
//        // Link permissions to the role
//        for (Permissions permission : rolePermissions) { 
//            Role_Permissions rolePermissionsEntity = new Role_Permissions();
//            rolePermissionsEntity.setRole(role);
//            rolePermissionsEntity.setPermission(permission);
//            rolePermissionsService.saveRolePermissions(rolePermissionsEntity);
//        }
//
//        return ResponseEntity.ok("Role '" + role.getRoleType() + "' linked to corresponding permissions.");
//    }

}
