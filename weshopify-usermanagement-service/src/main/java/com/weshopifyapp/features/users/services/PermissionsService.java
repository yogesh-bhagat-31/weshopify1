package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;

public interface PermissionsService {
	
	PermissionsBean createPermission(PermissionsBean pbean) throws PermissionsNotFoundException;
	PermissionsBean updatePermission(PermissionsBean pbean) throws PermissionsNotFoundException;
	PermissionsBean findPermissionById(int permissionsId) throws PermissionsNotFoundException;
	List<PermissionsBean> deletePermissionById(int permissionsId);
	List<PermissionsBean> getAllPermissions();
	List<PermissionsBean> searchPermissions();

}
