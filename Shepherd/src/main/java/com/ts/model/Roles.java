package com.ts.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rollId;
	private String roleType;

	@OneToOne(mappedBy = "role", cascade = CascadeType.ALL)
	private Users user;

	public Long getRollId() {
		return rollId;
	}

	public void setRollId(Long rollId) {
		this.rollId = rollId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Roles(Long rollId, String roleType, Users user) {
		super();
		this.rollId = rollId;
		this.roleType = roleType;
		this.user = user;
	}

}
