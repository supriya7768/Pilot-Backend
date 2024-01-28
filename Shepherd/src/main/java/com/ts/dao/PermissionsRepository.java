package com.ts.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
	Optional<Permissions> findByPermissionType(String permissionType);

}
