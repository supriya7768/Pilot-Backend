package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.Permissions;
import com.ts.model.Role_Permissions;
import com.ts.model.Roles;

@Repository
public interface Role_PermissionsRepository extends JpaRepository<Role_Permissions, Long> {

	boolean existsByRoleAndPermission(Roles role, Permissions permission);

}
