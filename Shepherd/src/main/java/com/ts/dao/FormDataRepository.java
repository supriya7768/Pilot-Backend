package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.FormData;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {

}
