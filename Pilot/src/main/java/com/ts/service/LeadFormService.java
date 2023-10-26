package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.LeadFormRepository;
import com.ts.model.LeadForm;

@Service
public class LeadFormService {

	@Autowired
	LeadFormRepository lr;
	
	public LeadForm addLead(LeadForm leadForm) {
		return lr.save(leadForm);
	}
	

	public List<LeadForm> getAllLeadData() {
	    // Modify this method to retrieve all lead data
	    return lr.findAll(); // Assuming findAll() method exists in your repository
	}

}
