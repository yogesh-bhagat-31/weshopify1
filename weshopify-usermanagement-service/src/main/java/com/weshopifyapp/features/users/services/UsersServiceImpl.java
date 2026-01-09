package com.weshopifyapp.features.users.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.commons.beans.EmailRequest;
import com.weshopifyapp.features.commons.beans.NotificationsBean;
import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.beans.UsersBean;
import com.weshopifyapp.features.users.clients.NotificationServiceRestClient;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;
import com.weshopifyapp.features.users.data.exceptions.UsersNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.models.RoleToPermisions;
import com.weshopifyapp.features.users.data.models.Roles;
import com.weshopifyapp.features.users.data.models.Users;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;
import com.weshopifyapp.features.users.data.repository.RolesRepository;
import com.weshopifyapp.features.users.data.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
	private Roles roles;

	private UsersRepository usersRepository;
	private ModelMapper modelMapper;
	private RolesRepository rolesRepository;
	NotificationServiceRestClient notificationServiceRestClient;

	@Value("${weshopify.self-reg.role}")
	private String selfRegRole;

	@Value("${mailJet.api.subject}")
	private String mailJetSubject;

	public UsersServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper, RolesRepository rolesRepository,
			NotificationServiceRestClient notificationServiceRestClient) {
		super();
		this.usersRepository = usersRepository;
		this.modelMapper = modelMapper;
		this.rolesRepository = rolesRepository;
		this.notificationServiceRestClient = notificationServiceRestClient;
	}

	@Override
	public UsersBean createUser(UsersBean usersBean) {
		Users users = provisioning(usersBean);
		usersRepository.save(users);
		// send an email
		if (users != null && users.getId() > 0) {

			EmailRequest emailRequest = EmailRequest.builder().email(users.getEmail()).id(users.getId())
					.userId(users.getUserId()).build();

			NotificationsBean notificationsBean = NotificationsBean.builder().subject(mailJetSubject)
					.to(Arrays.asList(emailRequest)).build();
			
			notificationServiceRestClient.sendVerificationEmail(notificationsBean);

		}
		return mapEntityToBean(users);
	}

	@Override
	public UsersBean updateUser(UsersBean usersBean) {
		Users users = provisioning(usersBean);
		if ("deProvision".equals(usersBean.getUserRoles().getOperation())) {
			deProvisioning(users);
			log.info("User deprovisoined is: " + users.toString());
		}

		usersRepository.save(users);
		return mapEntityToBean(users);
	}

	@Override
	public UsersBean findUserById(int id) throws UsersNotFoundException {
		Users user = usersRepository.findById(id).orElseThrow(
				() -> UsersNotFoundException.builder().message("User is not present in Db with id:/t" + id).build());
		return mapEntityToBean(user);
	}

	@Override
	public UsersBean findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsersBean> findAllUsers() {
		List<UsersBean> userBean = new ArrayList<>();
		Optional.ofNullable(usersRepository.findAll()).ifPresentOrElse(

				listOfUsers -> listOfUsers.stream().forEach(users -> userBean.add(mapEntityToBean(users))),

				() -> UsersNotFoundException.builder().message("No users found:- ").build()

		);
		return userBean;
	}

	@Override
	public List<UsersBean> findAllUsers(int currPage, int noOfRecPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsersBean> deleteUser(UsersBean usersBean) throws UsersNotFoundException {
		Optional.ofNullable(usersRepository.findById(usersBean.getId())).orElseThrow(() -> UsersNotFoundException
				.builder().message("User Not Present In Db with userId:/t" + usersBean.getId()).build());
		usersRepository.delete(mapBeanToEntity(usersBean));
		return findAllUsers();
	}

	@Override
	public List<UsersBean> deleteUserById(int id) throws UsersNotFoundException {
		Optional.ofNullable(usersRepository.findById(id)).orElseThrow(
				() -> UsersNotFoundException.builder().message("User Not Present In Db with userId:/t" + id).build());
		usersRepository.deleteById(id);
		return findAllUsers();
	}

	@Override
	public List<UsersBean> searchUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users provisioning(UsersBean usersBean) throws RolesNotFoundException {
		Users usersEntity;

		if (usersBean.getUserRoles() != null) {
			roles = Optional.ofNullable(usersBean.getUserRoles())
					.map(role -> rolesRepository.findById(role.getRoleId()).get())
					.get();
			if (usersBean.getEmail() != null) {
				usersEntity = mapBeanToEntity(usersBean);
				// Updating User
				if (usersBean.getId() > 0 && usersRepository.existsById(usersBean.getId())) {
					usersEntity.setUserRoles(roles);

				} else {
					// Creating User
					usersEntity = mapBeanToEntity(usersBean);
					usersEntity.setUserRoles(roles);
				}
			} else {
				usersEntity = null;
				// Updating User
				if (usersBean.getId() > 0 && usersRepository.existsById(usersBean.getId())) {
					usersEntity = usersRepository.findById(usersBean.getId()).get();
					usersEntity.setUserRoles(roles);

				}
			}
		} else {

			usersEntity = mapBeanToEntity(usersBean);
			usersEntity.setUserRoles(roles);
		}

		return usersEntity;
	}

	@Override
	public Users deProvisioning(Users users) {
		users.setUserRoles(null);
		return users;
	}

	@Override
	public UsersBean enableUser(int id) throws UsersNotFoundException {
//		Users user = Optional.ofNullable(usersRepository.findById(id)).get().orElseThrow(
//				() -> UsersNotFoundException.builder().message("User Not Present In Db with userId:/t" + id).build());
//		user.setEnabled(true);
//		usersRepository.save(user);
//		return mapEntityToBean(user);

		return provisioningRoleToUser(id);
	}

	@Override
	public UsersBean unlockUser(int id) throws UsersNotFoundException {
		Users user = Optional.ofNullable(usersRepository.findById(id)).get().orElseThrow(
				() -> UsersNotFoundException.builder().message("User Not Present In Db with userId:/t" + id).build());
		user.setLocked(false);
		usersRepository.save(user);
		return mapEntityToBean(user);
	}

	private Users mapBeanToEntity(UsersBean usersBean) {

		Users users = modelMapper.map(usersBean, Users.class);
		return users;

	}

	private UsersBean mapEntityToBean(Users users) {

		return modelMapper.map(users, UsersBean.class);
	}

	@Override
	public UsersBean provisioningRoleToUser(int userId) {
		// Here user id will be the part of payload, and role id we will take it from
		// configuratio
		// file that is from application.properties.
		RolesBean rolesBean = RolesBean.builder().name(selfRegRole).build();
		UsersBean usersBean = UsersBean.builder().id(userId).userRoles(rolesBean).build();

		// Here we get already existed role inside db that is customer now.
		Roles roles = Optional.ofNullable(usersBean.getUserRoles())
				.map(r -> rolesRepository.findByName(r.getName()).get()).get();

		// Here we will get the userBean from Db
		Users usersEntity = usersRepository.findById(usersBean.getId()).get();
		usersEntity.setUserRoles(roles);
		usersEntity.setEnabled(true);
		usersEntity.setLocked(false);

		return mapEntityToBean(usersRepository.save(usersEntity));

	}

	// =============Sir Chnaged the logic but both are correct==========//
//	public UsersBean provisioningRoleToUser1(int userId) {
//		// We got roles from db
//		Roles roles = rolesRepository.findByName("Customer").get();	
//		
//		// We got user from db
//		Users users = usersRepository.findById(userId).get();
//		
//		//Assign the role to this user
//		users.setUserRoles(roles);
//		
//		// finally we persisted data in db
//		
//		return   mapEntityToBean(usersRepository.save(users));
//	}

}
