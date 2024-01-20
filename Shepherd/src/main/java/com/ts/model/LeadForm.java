package com.ts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeadForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String mobile;
	private String address;
	private String courseIntrested;
	private String mode;
	private String courseDoneInOtherInstitute;
	private String instituteName;
	private String reason;
	private String interest;
	private String degree;
	private String field;
	private int passingYear;
	private String collegeName;
	private String experience;
	private String designation;
	private String approach;
	private String referenceName;
	private String batchCode;
	private String status;
	private String comment;
	private String follow;
	private String date;

	private String latestComment;
	private String latestFollow;
	private String latestStatus;

	@OneToMany(mappedBy = "leadForm", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FollowComment> followComments;

	@OneToMany(mappedBy = "leadForm", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Invoice> invoices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCourseIntrested() {
		return courseIntrested;
	}

	public void setCourseIntrested(String courseIntrested) {
		this.courseIntrested = courseIntrested;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCourseDoneInOtherInstitute() {
		return courseDoneInOtherInstitute;
	}

	public void setCourseDoneInOtherInstitute(String courseDoneInOtherInstitute) {
		this.courseDoneInOtherInstitute = courseDoneInOtherInstitute;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(int passingYear) {
		this.passingYear = passingYear;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getApproach() {
		return approach;
	}

	public void setApproach(String approach) {
		this.approach = approach;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public List<FollowComment> getFollowComments() {
		return followComments;
	}

	public void setFollowComments(List<FollowComment> followComments) {
		this.followComments = followComments;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public LeadForm(Long id, String name, String email, String mobile, String address, String courseIntrested,
			String mode, String courseDoneInOtherInstitute, String instituteName, String reason, String interest,
			String degree, String field, int passingYear, String collegeName, String experience, String designation,
			String approach, String referenceName, String batchCode, String status, String comment, String follow,
			String date, String latestComment, String latestFollow, String latestStatus,
			List<FollowComment> followComments, List<Invoice> invoices) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.courseIntrested = courseIntrested;
		this.mode = mode;
		this.courseDoneInOtherInstitute = courseDoneInOtherInstitute;
		this.instituteName = instituteName;
		this.reason = reason;
		this.interest = interest;
		this.degree = degree;
		this.field = field;
		this.passingYear = passingYear;
		this.collegeName = collegeName;
		this.experience = experience;
		this.designation = designation;
		this.approach = approach;
		this.referenceName = referenceName;
		this.batchCode = batchCode;
		this.status = status;
		this.comment = comment;
		this.follow = follow;
		this.date = date;
		this.latestComment = latestComment;
		this.latestFollow = latestFollow;
		this.latestStatus = latestStatus;
		this.followComments = followComments;
		this.invoices = invoices;
	}

	public LeadForm() {
	}
}
