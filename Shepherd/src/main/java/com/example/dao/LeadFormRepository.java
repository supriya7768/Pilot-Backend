package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LeadForm;

@Repository
public interface LeadFormRepository extends JpaRepository<LeadForm, Long> {

    Optional<LeadForm> findByEmail(String email);

    Optional<LeadForm> findByMobile(String mobile);

    List<LeadForm> findByStatus(String status);

	LeadForm findByEmailOrMobile(String email, String mobile);
}