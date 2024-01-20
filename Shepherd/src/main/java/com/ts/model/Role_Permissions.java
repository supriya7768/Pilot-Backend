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

	public Long getRpId() {
		return rpId;
	}

	public void setRpId(Long rpId) {
		this.rpId = rpId;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Permissions getPermission() {
		return permission;
	}

	public void setPermission(Permissions permission) {
		this.permission = permission;
	}

	public Role_Permissions(Long rpId, Roles role, Permissions permission) {
		this.rpId = rpId;
		this.role = role;
		this.permission = permission;
	}

	public Role_Permissions() {
	}
}