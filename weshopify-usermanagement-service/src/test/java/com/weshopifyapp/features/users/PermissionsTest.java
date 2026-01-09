package com.weshopifyapp.features.users;



import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

@TestMethodOrder(OrderAnnotation.class)
public class PermissionsTest extends WeshopifyUsermanagementServiceApplicationTests {

	@Autowired
	private PermissionsRepository permissionsRepo;

	@Test
	@Order(1)
	public void createPermissions() {

		Permissions view = Permissions.builder().name("view").build();
		permissionsRepo.save(view);
		assertNotNull(view.getPermissionId());
		assertNotNull(view.getName());

	}

}
