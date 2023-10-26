package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.LeadForm;

@Repository
public interface LeadFormRepository extends JpaRepository<LeadForm, Long> {
	
}

