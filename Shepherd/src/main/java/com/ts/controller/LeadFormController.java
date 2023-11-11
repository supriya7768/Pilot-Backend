package com.ts.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/get-lead-data-dashboard")
	@ResponseBody
	public List<LeadForm> getLeadDataDashboard() {
	    List<LeadForm> leadData = ls.getAllLeadData();
	    return leadData;
	}
	
	@PutMapping("/update-lead/{id}")
    public ResponseEntity<String> updateLeadStatus(@PathVariable Long id, @RequestBody String selectedStatus) {
        try {
            // Call your service to update the lead status in the database.
            ls.updateLeadStatus(id, selectedStatus);
            return ResponseEntity.ok(selectedStatus);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update lead status.");
        }
    }
	
	@GetMapping("/get-all-lead-counts")
    public Map<String, Integer> getAllLeadCounts() {
        // Fetch all lead data
        List<LeadForm> allLeadData = ls.getAllLeadDataDashboard();

        // Count leads for each date
        Map<String, Integer> leadCounts = allLeadData.stream()
                .collect(Collectors.groupingBy(LeadForm::getFollow, Collectors.summingInt(lead -> 1)));

        return leadCounts;
    }
	
}
