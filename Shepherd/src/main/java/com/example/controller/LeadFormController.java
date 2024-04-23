package com.example.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LeadForm;
import com.example.service.LeadFormService;

import jakarta.persistence.EntityNotFoundException;

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
	public List<LeadForm> getLeadData() {
		List<LeadForm> leadData = ls.getAllLeadData();
		return leadData;
	}

	@GetMapping("/get-lead-data-dashboard")
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

			// If status is "Deal Done" or "Close", set the follow date to an empty string
			if ("Deal Done".equals(selectedStatus) || "Close".equals(selectedStatus)) {
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
	public Map<String, Integer> getAllLeadCountsByMonth() {
		// Get the current month
		YearMonth currentMonth = YearMonth.now();

		// Fetch all lead data
		List<LeadForm> allLeadData = ls.getAllLeadDataDashboard();

		// Filter out leads with status "Deal Done" or "Close" for the current month
		List<LeadForm> filteredLeads = allLeadData.stream()
				.filter(lead -> !"Deal Done".equals(lead.getStatus()) && !"Close".equals(lead.getStatus()))
				.filter(lead -> {
					String followDateStr = lead.getFollow();
					if (followDateStr == null || followDateStr.trim().isEmpty()) {
						return false; // Skip leads with empty or whitespace date strings
					}

					LocalDate leadDate;
					try {
						leadDate = LocalDate.parse(followDateStr); // Assuming "yyyy-MM-dd" format
					} catch (DateTimeParseException e) {
						return false; // Skip leads with invalid date strings
					}

					return leadDate.getMonth() == currentMonth.getMonth();
				}).collect(Collectors.toList());

		// Count leads for each date
		Map<String, Integer> leadCounts = filteredLeads.stream()
				.collect(Collectors.groupingBy(LeadForm::getFollow, Collectors.summingInt(lead -> 1)));

		return leadCounts;
	}

	// The other two methods (getTotalLeadCountsByMonth and
	// getTotalLeadCountsDoneByMonth) can use similar modifications.

	@GetMapping("/get-total-lead-counts")
	public Long getTotalLeadCountsByMonth() {
		// Get the current month
		YearMonth currentMonth = YearMonth.now();

		List<LeadForm> leads = ls.getAllLeadDataDashboard(); // Modify accordingly

		// Filter leads by current month and count them
		long count = leads.stream().filter(lead -> {
			String followDateStr = lead.getFollow();
			if (followDateStr == null || followDateStr.trim().isEmpty()) {
				return false; // Skip leads with empty or whitespace date strings
			}

			LocalDate leadDate;
			try {
				leadDate = LocalDate.parse(followDateStr); // Assuming "yyyy-MM-dd" format
			} catch (DateTimeParseException e) {
				return false; // Skip leads with invalid date strings
			}

			return leadDate.getMonth() == currentMonth.getMonth();
		}).count();

		return count;
	}

	@GetMapping("/get-total-lead-counts-done")
	public Long getTotalLeadCountsDoneByMonth() {
		// Get the current month
		YearMonth currentMonth = YearMonth.now();

		List<LeadForm> leads = ls.getAllLeadDataDashboard(); // Modify accordingly

		// Filter leads with status "Deal Done" by current month and count them
		long count = leads.stream().filter(lead -> "Deal Done".equals(lead.getStatus())).filter(lead -> {
			String followDateStr = lead.getFollow();
			if (followDateStr == null || followDateStr.trim().isEmpty()) {
				return false; // Skip leads with empty or whitespace date strings
			}

			LocalDate leadDate;
			try {
				leadDate = LocalDate.parse(followDateStr); // Assuming "yyyy-MM-dd" format
			} catch (DateTimeParseException e) {
				return false; // Skip leads with invalid date strings
			}

			return leadDate.getMonth() == currentMonth.getMonth();
		}).count();

		return count;
	}

	@GetMapping("/get-total-lead-counts-close")
	public Long getTotalLeadCountsCloseByMonth() {
		// Get the current month
		YearMonth currentMonth = YearMonth.now();

		List<LeadForm> leads = ls.getAllLeadDataDashboard(); // Modify accordingly

		// Filter leads with status "Close" by current month and count them
		long count = leads.stream().filter(lead -> "Close".equals(lead.getStatus())).filter(lead -> {
			String followDateStr = lead.getFollow();
			if (followDateStr == null || followDateStr.trim().isEmpty()) {
				return false; // Skip leads with empty or whitespace date strings
			}

			LocalDate leadDate;
			try {
				leadDate = LocalDate.parse(followDateStr); // Assuming "yyyy-MM-dd" format
			} catch (DateTimeParseException e) {
				return false; // Skip leads with invalid date strings
			}

			return leadDate.getMonth() == currentMonth.getMonth();
		}).count();

		return count;
	}

	// ===============================================================================================

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
