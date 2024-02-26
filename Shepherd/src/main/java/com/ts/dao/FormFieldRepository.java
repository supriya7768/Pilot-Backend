package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.FormField;

@Repository
public interface FormFieldRepository extends JpaRepository<FormField, Long> {

}
