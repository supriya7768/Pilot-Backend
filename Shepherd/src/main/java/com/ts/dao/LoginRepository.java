package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByEmail(String email);

	Login findByEmailAndPassword(String email, String password);

}
