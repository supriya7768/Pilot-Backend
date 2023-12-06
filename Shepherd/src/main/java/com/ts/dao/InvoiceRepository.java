package com.ts.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ts.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	Optional<Invoice> findByEmail(String email);
	
	Optional<Invoice> findByMobile(String mobile);
	
	@Query("SELECT MAX(i.invoiceId) FROM Invoice i")
    Long findMaxInvoiceId();
}
