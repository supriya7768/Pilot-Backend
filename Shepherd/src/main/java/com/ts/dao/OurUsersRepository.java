package com.ts.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ts.model.OurUsers;

public interface OurUsersRepository extends JpaRepository<OurUsers, Integer> {
	 @Query(value = "select * from ourusers where email = ?1", nativeQuery = true)
	    Optional<OurUsers> findByEmail(String email);
}
