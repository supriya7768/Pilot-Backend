package com.ts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FollowComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String latestComment;
	private String latestFollow;
	private String latestStatus;

	@ManyToOne
	private LeadForm leadForm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLatestComment() {
		return latestComment;
	}

	public void setLatestComment(String latestComment) {
		this.latestComment = latestComment;
	}

	public String getLatestFollow() {
		return latestFollow;
	}

	public void setLatestFollow(String latestFollow) {
		this.latestFollow = latestFollow;
	}

	public String getLatestStatus() {
		return latestStatus;
	}

	public void setLatestStatus(String latestStatus) {
		this.latestStatus = latestStatus;
	}

	public LeadForm getLeadForm() {
		return leadForm;
	}

	public void setLeadForm(LeadForm leadForm) {
		this.leadForm = leadForm;
	}

	public FollowComment(Long id, String latestComment, String latestFollow, String latestStatus, LeadForm leadForm) {
		super();
		this.id = id;
		this.latestComment = latestComment;
		this.latestFollow = latestFollow;
		this.latestStatus = latestStatus;
		this.leadForm = leadForm;
	}

	public FollowComment() {
	}
}
