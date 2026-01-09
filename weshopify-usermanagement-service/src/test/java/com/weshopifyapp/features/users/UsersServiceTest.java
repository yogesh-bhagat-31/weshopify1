package com.weshopifyapp.features.users;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.beans.UsersBean;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;
import com.weshopifyapp.features.users.services.UsersService;

@TestMethodOrder(OrderAnnotation.class)
public class UsersServiceTest extends RolesTest {
	
	@Autowired
	UsersService usersService;
	
	@Test
	@Order(3)
	public void testCreateUser() throws RolesNotFoundException {
		RolesBean rolesBean = RolesBean.builder().name("Admin").roleId(1).build();
		UsersBean usersBean = UsersBean.builder()
				                       .fname("Yogesh")
				                       .lname("B")
				                       .userId("yogesh@123")
				                       .mobile("9996669996")
				                       .email("yogesh@123gmail.com")
				                       .isEnabled(false)
				                       .isLocked(true)
				                       .userRoles(rolesBean)
				                       .build();
		usersService.createUser(usersBean);
	}
		@Test
		@Order(4)
		public void testUpdateUser() throws RolesNotFoundException {
			RolesBean rolesBean = RolesBean.builder().name("Admin").roleId(1).operation("deProvision").build();
			UsersBean usersBean = UsersBean.builder()
					                       .id(1)
					                       .fname("Yogesh")
					                       .lname("B")
					                       .userId("yogesh@123")
					                       .mobile("9996669996")
					                       .email("yogesh@123gmail.com")
					                       .isEnabled(false)
					                       .isLocked(true)
					                       .userRoles(rolesBean)
					                       .build();
			usersService.updateUser(usersBean);
   }
		@Test
		@Order(5)
		public void testUpdateProvisionUser() throws RolesNotFoundException {
			RolesBean rolesBean = RolesBean.builder().name("Admin").roleId(1).build();
			UsersBean usersBean = UsersBean.builder()
					.id(1)
					.fname("Yogesh")
					.lname("B")
					.userId("yogesh@123")
					.mobile("9996669996")
					.email("yogesh@123gmail.com")
					.isEnabled(false)
					.isLocked(true)
					.userRoles(rolesBean)
					.build();
			usersService.updateUser(usersBean);
		}

}