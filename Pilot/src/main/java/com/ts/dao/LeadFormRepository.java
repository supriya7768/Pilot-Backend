package com.ts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ts.model.LeadForm;
import com.ts.model.LeadProjection;

@Repository
public interface LeadFormRepository extends JpaRepository<LeadForm, Long> {
    List<LeadProjection> findByName(String name);
}

