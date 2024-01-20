package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.Role_Permissions;

@Repository
public interface Role_PermissionsRepository extends JpaRepository<Role_Permissions, Long> {

}
