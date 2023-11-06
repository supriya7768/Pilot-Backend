package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/get-lead-data-dashboard")
	@ResponseBody
	public List<LeadForm> getLeadDataDashboard() {
	    List<LeadForm> leadData = ls.getAllLeadData();
	    return leadData;
	}
	

	@GetMapping("/active")
    public List<LeadForm> getActiveLeads() {
        return ls.getActiveLeads();
    }

    @GetMapping("/student")
    public List<LeadForm> getStudentLeads() {
        return ls.getStudentLeads();
    }

    @PutMapping("/{leadId}/status")
    public ResponseEntity<LeadForm> updateLeadStatus(@PathVariable Long leadId, @RequestParam String newStatus) {
        LeadForm updatedLead = ls.updateLeadStatus(leadId, newStatus);
        if (updatedLead != null) {
            return ResponseEntity.ok(updatedLead);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


	
}
