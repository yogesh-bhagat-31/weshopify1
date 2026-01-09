package com.weshopifyapp.features.users.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PermissionsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1408267264391538921L;

	private int permissionId;

	private String name;

}
