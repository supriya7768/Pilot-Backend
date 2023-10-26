package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.LeadForm;
import com.ts.model.LeadProjection;
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
	
	 @GetMapping("/get-lead")
	    public List<LeadProjection> getLeadDataByName(@RequestParam String name) {
	        return ls.getLeadDataByName(name);
	    }
	
}
