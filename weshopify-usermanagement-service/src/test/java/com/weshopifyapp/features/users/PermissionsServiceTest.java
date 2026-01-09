package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.services.PermissionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class PermissionsServiceTest extends WeshopifyUsermanagementServiceApplicationTests {
	
	private static int PERMISSION_ID= 1;
	
	@Autowired
	PermissionsService permissionsService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// We can write something that will run before each method
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// We can write something that will run after each method

	}

//	@Test
//	void testPermissionsImpl() {
//		fail("Not yet implemented");
//	}

	@Test
	@Order(value = 1)
	void testCreatePermission() throws PermissionsNotFoundException {
		PermissionsBean permissionsBean = PermissionsBean.builder().name("Create").build();
		permissionsBean = permissionsService.createPermission(permissionsBean);
		log.info("permissions created are:\t" + permissionsBean.toString()); // This just for log purpose , this is for us, not good practice in unit testing.
		assertNotEquals(0, permissionsBean.getPermissionId()); // means whatever the id generated should not be zero it shoule be more than that.
		
	}

	@Test
	@Order(value = 2)
	void testUpdatePermission() throws PermissionsNotFoundException {
		PermissionsBean permissionsBean = PermissionsBean.builder().name("View").permissionId(PERMISSION_ID).build();
		permissionsBean = permissionsService.updatePermission(permissionsBean);
		log.info("permissions updated are:\t" + permissionsBean.toString());
		assertEquals("View", permissionsBean.getName());
		
	}

	@Test
	@Order(value = 3)
	void testFindPermissionById() throws PermissionsNotFoundException {
		PermissionsBean permissionsBean =permissionsService.findPermissionById(PERMISSION_ID);
		log.info("permission is:\t" + permissionsBean.toString());
		assertNotNull(permissionsBean);
		assertNotEquals(0, permissionsBean.getPermissionId());
		assertEquals(PERMISSION_ID, permissionsBean.getPermissionId());
		assertEquals("View", permissionsBean.getName());
		
	}

//	@Test
//	@Order(value = 5)
//	void testDeletePermissionById() {
//		List<PermissionsBean> list = permissionsService.deletePermissionById(PERMISSION_ID);
//		log.info("list after permission deleted is:\t" +list);
//		
//	}

	@Test
	@Order(value = 4)
	void testGetAllPermissions() {	
		List<PermissionsBean> list = permissionsService.getAllPermissions();
		log.info("getting all permissions:\t" +list);
		assertNotNull(list);
		assertNotEquals(0, list.size());
		
	}

	@Test
	void testSearchPermissions() {
//		fail("Not yet implemented");
	}

}
