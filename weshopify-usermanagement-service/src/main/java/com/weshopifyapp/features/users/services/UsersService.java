package com.weshopifyapp.features.users.services;

import java.util.List;

import com.weshopifyapp.features.users.beans.UsersBean;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;
import com.weshopifyapp.features.users.data.exceptions.UsersNotFoundException;
import com.weshopifyapp.features.users.data.models.Users;

public interface UsersService {
	
	UsersBean createUser(UsersBean usersBean) throws RolesNotFoundException;
	UsersBean updateUser(UsersBean usersBean);
	UsersBean findUserById(int id) throws UsersNotFoundException;
	UsersBean findUserByEmail(String email);
	List<UsersBean> findAllUsers();
	List<UsersBean> findAllUsers(int currPage, int noOfRecPerPage);
	List<UsersBean> deleteUser(UsersBean usersBean) throws UsersNotFoundException;
	List<UsersBean> deleteUserById(int id) throws UsersNotFoundException;
	
	/**
	 * Search criteria yet to decides
	 */
	List<UsersBean> searchUsers();
	
	/**
	 * Assigning the role to a user is called as provisioning
	 * @throws RolesNotFoundException 
	 */
	Users provisioning(UsersBean user);

	/**
	 * Un assign the role to a user is called as de-provisioning
	 */
	Users deProvisioning(Users user);
	
	/**
	 * By Default Every Created User will be in the disabled and locked state
	 * we have to enable the user and unlock the user
	 * @throws UsersNotFoundException 
	 */
	// Example here I have not used debit card for 90 days , my debit card will be disabled.

	UsersBean enableUser(int id) throws UsersNotFoundException;
	

	// For fraudelent transaction debit card will be blocked/locked. This just for info
	UsersBean unlockUser(int id) throws UsersNotFoundException;
	
	UsersBean provisioningRoleToUser(int userId);
	
	
}
