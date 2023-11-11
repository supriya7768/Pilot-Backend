package com.ts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.LeadFormRepository;
import com.ts.model.LeadForm;

@Service
public class LeadFormService {

	@Autowired
	LeadFormRepository lr;

	public LeadForm addLead(LeadForm leadForm) {

		if (lr.findByEmail(leadForm.getEmail()).isPresent()) {
			return new LeadForm();
		}
		if (lr.findByMobile(leadForm.getMobile()).isPresent()) {
			return new LeadForm();
		} else {
			return lr.save(leadForm);
		}
	}

	public List<LeadForm> getAllLeadData() {
		// Modify this method to retrieve all lead data
		return lr.findAll(); // Assuming findAll() method exists in your repository
	}

	public List<LeadForm> getAllLeadDataDashboard() {
		// Modify this method to retrieve all lead data
		return lr.findAll(); // Assuming findAll() method exists in your repository
	}

	public void updateLeadStatus(Long id, String selectedStatus) {
	    // Retrieve the lead from the database by leadId.
	    Optional<LeadForm> optionalLead = lr.findById(id);

	    if (optionalLead.isPresent()) {
	        LeadForm lead = optionalLead.get();
	        // Update the status of the lead.
	        lead.setStatus(selectedStatus);
	        // Save the updated lead back to the database.
	        lr.save(lead);
	    }
	}

}