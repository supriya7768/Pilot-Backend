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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role_Permissions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rpId;

	@ManyToOne
	private Roles role;

	@ManyToOne
	private Permissions permission;

}