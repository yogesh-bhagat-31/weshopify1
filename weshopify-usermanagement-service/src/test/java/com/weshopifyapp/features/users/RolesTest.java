package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;


import com.weshopifyapp.features.users.data.models.RoleToPermisions;
import com.weshopifyapp.features.users.data.models.Roles;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;
import com.weshopifyapp.features.users.data.repository.RolesRepository;


@TestMethodOrder(OrderAnnotation.class)
public class RolesTest extends PermissionsTest {
	
	@Autowired
	private RolesRepository rolesRepo;
	
	@Autowired
	private PermissionsRepository permissions;
	
	
	@Test
	@Order(2)
	public void testCreateRole(){  
		
//		RoleToPermisions rolesToperm = RoleToPermisions.builder().permissions(permissions.findById(1).get()).build();
//		
//		Roles roles = Roles.builder()
//				         .name("name")
//		                 .role_to_permissions(Arrays.asList(rolesToperm))
//		                 .build();
//		rolesRepo.save(roles);
//		assertNotNull(roles.getRole_to_permissions());
//		assertNotNull(roles.getRoleId());
		
		//==================Niche wala info Jab Hum Mock use karenge tab ke liye hai
		//Extend karne ki siva hum ye bhi kar sakte hai.
		//    Permissions permission = Permissions.builder().name("view").build();
        //permissionsRepo.save(permission);
		// Yaha pe extend karke kuch fayda nahi hoga sirf hume methods milenge PermissionTest class ke 
		// Matlab yaha pe permission pehle create karna padega agar hum mock use karte hai to as database ko hum mock main touch nahi karte.
		
		Roles role = new Roles();
		
		RoleToPermisions rolesToPerm = RoleToPermisions.builder()
				                       .permissions(permissions.findById(1).get())				                      
				                       .roles(role)
				                       .build(); 
		
		role.setRole_to_permissions(Arrays.asList(rolesToPerm));
		role.setName("Admin");
		rolesRepo.save(role);
		assertNotNull(role.getRole_to_permissions());
		assertNotNull(role.getRoleId());
//		
		 
	} 

}
