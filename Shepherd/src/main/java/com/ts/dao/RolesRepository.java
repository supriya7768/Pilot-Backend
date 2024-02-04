package com.ts.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	Optional<Roles> findByRoleType(String roleType);

}