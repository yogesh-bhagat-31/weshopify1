package com.weshopifyapp.features.users.beans;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesBean implements Serializable {
    
	private static final long serialVersionUID = 3620413382483176059L;
	private int roleId;
	private String name;
	private List<PermissionsBean> permissions;
	/**
	 *do action that is provision/deprovision 
	 */
	private String operation;
}
