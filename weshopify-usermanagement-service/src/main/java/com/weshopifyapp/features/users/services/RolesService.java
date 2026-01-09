package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;


public interface RolesService {
	
	
	RolesBean createRoles(RolesBean rbean) throws RolesNotFoundException;
	RolesBean updateRoles(RolesBean rbean) throws RolesNotFoundException;
	RolesBean findRoleById(int rolesId) throws RolesNotFoundException;
	List<RolesBean> deleteRoleById(int rolesId) throws RolesNotFoundException ;
	List<RolesBean> getAllRoles() throws RolesNotFoundException;
	List<RolesBean> searchRoles();

}
