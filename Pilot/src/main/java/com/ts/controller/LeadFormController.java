package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.LeadForm;
import com.ts.service.LeadFormService;

@CrossOrigin("*")
@RestController
public class LeadFormController {

	@Autowired
	LeadFormService ls;

	@PostMapping("/add-lead")
	public LeadForm addLead(@RequestBody LeadForm leadForm) {
		return ls.addLead(leadForm);

	}
	
	@GetMapping("/get-lead-data")
	@ResponseBody
	public List<LeadForm> getLeadData() {
	    List<LeadForm> leadData = ls.getAllLeadData();
	    return leadData;
	}
	
	

	
}
