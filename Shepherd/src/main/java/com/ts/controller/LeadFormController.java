package com.ts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public String addLead(@RequestBody LeadForm leadForm) {
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
	public ResponseEntity<Map<String, String>> updateLeadStatus(@PathVariable Long id,
			@RequestBody Map<String, String> requestBody) {
		try {
			String selectedStatus = requestBody.get("status");

			// Call your service to update the lead status in the database.
			ls.updateLeadStatus(id, selectedStatus);

			// If status is "Done" or "Close", set the follow date to an empty string
			if ("Done".equals(selectedStatus) || "Close".equals(selectedStatus)) {
				ls.updateFollowDate(id, ""); // Update the follow date in your service method
			}

			// Return a JSON object with the updated status
			Map<String, String> response = new HashMap<>();
			response.put("status", selectedStatus);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/get-all-lead-counts")
	public Map<String, Integer> getAllLeadCounts() {
		// Fetch all lead data
		List<LeadForm> allLeadData = ls.getAllLeadDataDashboard();

		// Filter out leads with status "Done" or "Closed"
		List<LeadForm> filteredLeads = allLeadData.stream()
				.filter(lead -> !"Done".equals(lead.getStatus()) && !"Closed".equals(lead.getStatus()))
				.collect(Collectors.toList());

		// Count leads for each date
	    Map<String, Integer> leadCounts = filteredLeads.stream()
	            .collect(Collectors.groupingBy(LeadForm::getFollow, Collectors.summingInt(lead -> 1)));

	    return leadCounts;
	}

	@PostMapping("/save-edits/{id}")
	public ResponseEntity<String> saveEdits(@PathVariable Long id, @RequestParam String newFollowUpDate,
			@RequestParam String newComment, @RequestParam String changeStatus) {
		try {
			ls.saveEdits(id, newFollowUpDate, newComment, changeStatus);
			return ResponseEntity.ok("Edit is saved successfully");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving edits");
		}
	}
}
