package com.ts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.dao.FollowCommentRepository;
import com.ts.dao.LeadFormRepository;
import com.ts.model.FollowComment;
import com.ts.model.LeadForm;

@Service
public class LeadFormService {

	@Autowired
	LeadFormRepository lr;

	@Autowired
	FollowCommentRepository followCommentRepository;

//	public LeadForm addLead(LeadForm leadForm) {
//
//		if (lr.findByEmail(leadForm.getEmail()).isPresent()) {
//			return new LeadForm();
//		}
//		if (lr.findByMobile(leadForm.getMobile()).isPresent()) {
//			return new LeadForm();
//		} else {
//			return lr.save(leadForm);
//		}
//	}

	public String addLead(LeadForm leadForm) {
		// Check if the email or mobile already exists
		if (lr.findByEmail(leadForm.getEmail()).isPresent() || lr.findByMobile(leadForm.getMobile()).isPresent()) {
			return "Entry not done. Email or mobile already exists.";
		} else {
			// Save the LeadForm to get the generated ID
			LeadForm savedLeadForm = lr.save(leadForm);

			// Check if the status is "Pending" or "Active"
			if ("Pending".equals(savedLeadForm.getStatus()) || "Active".equals(savedLeadForm.getStatus())) {
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
			if ("Done".equals(selectedStatus) || "Close".equals(selectedStatus)) {
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

}
