/**
 * 
 */
package com.weshopifyapp.features.users.data.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users implements Serializable {

	private static final long serialVersionUID = -4890746324619592473L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="firstName",nullable=false)
	private String fname;

	@Column(name = "lastName", nullable = false)
	private String lname;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "userId", nullable = false, unique = true)
	private String userId;

	@Column(name = "mobile", nullable = false, unique = true)
	private String mobile;

	@Column(name = "isEnabled", nullable = false)
	private boolean isEnabled;

	@Column(name = "isLocked", nullable = false)
	private boolean isLocked; // Here by default builder will take this value, even we can set our value while
								// creating object of this class

	@OneToOne
	private Roles userRoles;

}
