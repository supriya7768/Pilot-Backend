package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.FormField;

@Repository
public interface FormFieldRepository extends JpaRepository<FormField, Long> {

}
