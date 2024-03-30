package com.ts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.dao.FollowCommentRepository;
import com.ts.dao.LeadFormRepository;
import com.ts.model.FollowComment;
import com.ts.model.LeadForm;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LeadFormService {

	@Autowired
	LeadFormRepository lr;

	@Autowired
	FollowCommentRepository followCommentRepository;

	public String addLead(LeadForm leadForm) {
		// Check if the email or mobile already exists or if they are invalid
		if (lr.findByEmail(leadForm.getEmail()).isPresent() || lr.findByMobile(leadForm.getMobile()).isPresent()
				|| !isValidEmail(leadForm.getEmail()) || !isValidMobile(leadForm.getMobile())) {
			return "Entry not done. Email or mobile is invalid or already exists.";
		} else {
			// Save the LeadForm to get the generated ID
			LeadForm savedLeadForm = lr.save(leadForm);

			// Check if the status is "Contacted" or "Open" or "Proposal Sent"
			if ("Contacted".equals(savedLeadForm.getStatus()) || "Open".equals(savedLeadForm.getStatus())
					|| "Proposal Sent".equals(savedLeadForm.getStatus())) {
				FollowComment followComment = new FollowComment();
				followComment.setLatestFollow(savedLeadForm.getFollow());
				followComment.setLatestComment(savedLeadForm.getComment());
				followComment.setLatestStatus(savedLeadForm.getStatus());
				followComment.setLeadForm(savedLeadForm);

				// Save the FollowComment
				followCommentRepository.save(followComment);

				// Update the LeadForm with the FollowComment
				List<FollowComment> followComments = new ArrayList<>();
				followComments.add(followComment);
				savedLeadForm.setFollowComments(followComments);

				// Save the updated LeadForm
				savedLeadForm = lr.save(savedLeadForm);
			}

			return savedLeadForm.getName() + " added successfully.";
		}
	}

	private boolean isValidEmail(String email) {
		// Check if email is valid
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean isValidMobile(String mobile) {
		// Check if mobile number is 10 digits starting with 7, 8, or 9
		String mobileRegex = "^[7-9]\\d{9}$";
		Pattern pattern = Pattern.compile(mobileRegex);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public List<LeadForm> getAllLeadData() {
		return lr.findAll();
	}

	public List<LeadForm> getAllLeadDataDashboard() {
		return lr.findAll(); // Assuming findAll() method exists in your repository
	}

	public void updateLeadStatus(Long id, String selectedStatus) {
		Optional<LeadForm> optionalLead = lr.findById(id);

		if (optionalLead.isPresent()) {
			LeadForm lead = optionalLead.get();
			lead.setStatus(selectedStatus);
			if ("Deal Done".equals(selectedStatus) || "Close".equals(selectedStatus)) {
				lead.setFollow(""); // Assuming 'follow' is a field in your LeadForm class
			}
			lr.save(lead);
		}
	}

	public void updateFollowDate(Long id, String followDate) {
		Optional<LeadForm> optionalLead = lr.findById(id);

		if (optionalLead.isPresent()) {
			LeadForm lead = optionalLead.get();
			lead.setFollow(followDate);
			lr.save(lead);
		}
	}

	@Transactional
	public void saveEdits(Long id, String newFollowUpDate, String newComment, String changeStatus) {
		LeadForm leadForm = lr.findById(id).orElseThrow(EntityNotFoundException::new);

		// Save the new comment and follow-up in FollowComment
		FollowComment fc = new FollowComment();
		fc.setLeadForm(leadForm);
		fc.setLatestComment(newComment);
		fc.setLatestFollow(newFollowUpDate);
		fc.setLatestStatus(changeStatus);

		// Save the FollowComment first
		followCommentRepository.save(fc);

		if (leadForm.getFollowComments() == null) {
			leadForm.setFollowComments(new ArrayList<>());
		}

		leadForm.getFollowComments().add(fc);

		// Update LeadForm with the new comment and follow-up
		leadForm.setComment(newComment);
		leadForm.setFollow(newFollowUpDate);
		leadForm.setStatus(changeStatus);

		// Save the lead form with the new comment and follow-up
		lr.save(leadForm);
	}

	public Long getTotalId() {
		return lr.count();
	}


//	public List<LeadForm> getAllLeadDataForMonth(String monthYear, String status) {
//        int month = Integer.parseInt(monthYear.split("-")[1]);
//        int year = Integer.parseInt(monthYear.split("-")[0]);
//
//        LocalDate startDate = LocalDate.of(year, month, 1);
//        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
//
//        if (status != null) {
//            return lr.findByCreatedAtBetweenAndStatus(startDate, endDate, status);
//        } else {
//            return lr.findByCreatedAtBetween(startDate, endDate);
//        }
//    }
	
	 public String getLeadByEmail(String email) {
	        // Implement the business logic to check if the email already exists
	        // You can use leadRepository to interact with the database
	        // For example:
	        // Lead lead = leadRepository.findByEmail(email);
	        // if (lead != null) {
	        //     return "Email id is already present";
	        // } else {
	        //     return "Email id is not present";
	        // }

	        // For simplicity, returning a placeholder message
	        return "Email id is not present";
	    }

}
