package com.weshopifyapp.features.users.data.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.AssertFalse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles implements Serializable {

	private static final long serialVersionUID = -2333742779946866837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@Column(nullable = false,unique = true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
	private List<RoleToPermisions> role_to_permissions; // This is just for bidirectional mapping

	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

}
