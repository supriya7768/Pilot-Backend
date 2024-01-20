package com.ts.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Permissions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permissionsId;
	private String permissionType;

	@OneToMany(mappedBy = "permission", cascade = CascadeType.ALL)
	private List<Role_Permissions> rolePermissions;

	public Long getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Long permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public List<Role_Permissions> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<Role_Permissions> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public Permissions(Long permissionsId, String permissionType, List<Role_Permissions> rolePermissions) {
		super();
		this.permissionsId = permissionsId;
		this.permissionType = permissionType;
		this.rolePermissions = rolePermissions;
	}

	public Permissions() {
	}

}