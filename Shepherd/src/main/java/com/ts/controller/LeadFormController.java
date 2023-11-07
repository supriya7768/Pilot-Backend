package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	


    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam Long leadId, @RequestParam String newStatus) {
        ls.updateLeadStatus(leadId, newStatus);
        if ("done".equals(newStatus)) {
            ls.moveLeadToStudentPanel(leadId);
        }
        return ResponseEntity.ok("Status updated");
    }
    
//    @PostMapping("/updateStatus")
//    public String updateStatus(@RequestParam Long leadId, @RequestParam String newStatus, Model model) {
//        // Update the status in the backend
//
//        // Redirect to the student panel page
//        return "redirect:/studentpanel.html";
//    }
	
}
