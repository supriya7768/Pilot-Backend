package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.LeadFormRepository;
import com.ts.model.LeadForm;
import com.ts.model.LeadProjection;

@Service
public class LeadFormService {

	@Autowired
	LeadFormRepository lr;
	
	public LeadForm addLead(LeadForm leadForm) {
		return lr.save(leadForm);
	}
	
	 public List<LeadProjection> getLeadDataByName(String name) {
	        return lr.findByName(name);
	    }
}
