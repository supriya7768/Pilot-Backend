package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.InvoiceRepository;
import com.ts.dao.LeadFormRepository;
import com.ts.model.Invoice;
import com.ts.model.LeadForm;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository ir;

	@Autowired
	private LeadFormRepository leadFormRepository;

//	public String addInvoice(Invoice invoice) {
//		ir.save(invoice);
//		return "Invoice saved successfully.";
//
//	}
	
	public String addInvoice(Invoice invoice) {
        // Get the LeadForm based on email or mobile
        LeadForm leadForm = getLeadFormByEmailOrMobile(invoice.getEmail(), invoice.getMobile());

        // If leadForm is found, set the relationship and save
        if (leadForm != null) {
            invoice.setLeadForm(leadForm);
            leadForm.getInvoices().add(invoice);
            leadFormRepository.save(leadForm);
            return "Invoice saved successfully.";
        } else {
            return "LeadForm not found for the provided email or mobile.";
        }
    }
	
	// Helper method to find LeadForm by email or mobile
    private LeadForm getLeadFormByEmailOrMobile(String email, String mobile) {
        return leadFormRepository.findByEmailOrMobile(email, mobile);
    }


	public List<Invoice> getAllInvoiceData() {
		return ir.findAll();
	}

	public int getLastInvoiceId() {
		Long lastInvoiceId = ir.findMaxInvoiceId();
		return lastInvoiceId != null ? lastInvoiceId.intValue() : 0;
	}

	@Transactional
	public void saveLeadFormWithInvoice(LeadForm leadForm, Invoice invoice) {
		// Set the relationship bidirectionally
		invoice.setLeadForm(leadForm);
		leadForm.getInvoices().add(invoice);

		// Save the leadForm (which should cascade to save the associated invoices)
		leadFormRepository.save(leadForm);
	}
}
