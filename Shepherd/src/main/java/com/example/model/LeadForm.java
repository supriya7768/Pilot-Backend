package com.example.model;

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
}
