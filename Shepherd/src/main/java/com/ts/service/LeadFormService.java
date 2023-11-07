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

	public void updateLeadStatus(Long leadId, String newStatus) {
		Optional<LeadForm> lead = lr.findById(leadId);
		if (lead.isPresent()) {
			LeadForm updatedLead = lead.get();
			updatedLead.setStatus(newStatus);
			lr.save(updatedLead);
		}
	}

	public void moveLeadToStudentPanel(Long leadId) {
		// Implement logic to move the lead to the student panel
		// This could involve creating a new entity or updating an existing one
		// You may want to define a StudentPanelRepository and StudentPanel entity
	}
}
