package com.ts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ourusers")
public class OurUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identity gives additional use that it generates a primary key.
	private int id;
	private String userName;
	@Column(unique = true)
	private String email;
	private String password;
	private String roles;

}
