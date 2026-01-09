package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.Users;
import com.weshopifyapp.features.users.data.repository.RolesRepository;
import com.weshopifyapp.features.users.data.repository.UsersRepository;

public class UsersTest extends RolesTest {
	
	@Autowired
	UsersRepository usersRepo;
	
	@Autowired
	RolesRepository rolesRepo;
	
	
	@Test
	public void testCreateUSer(){
		
		Users users = Users.builder().userRoles(rolesRepo.findById(1).get())
				      .fname("Yogesh")
				      .lname("B")
				      .email("yb@gmail.com")
				      .userId("yb")
				      .mobile("9988552233")
				      .isEnabled(false)
				      .isLocked(true)
				      .build();
		usersRepo.save(users);
		assertNotNull(users);
		assertNotNull(users.getId());	
				     		
	}
	

}
