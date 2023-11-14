package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.InvoiceRepository;
import com.ts.model.Invoice;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository ir;
	
	public Invoice addInvoice(Invoice invoice) {
		return ir.save(invoice);
	}
	
	public List<Invoice> getAllInvoiceData() {
		return ir.findAll(); 
	}

}
