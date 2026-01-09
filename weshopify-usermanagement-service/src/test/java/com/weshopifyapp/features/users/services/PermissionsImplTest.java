package com.weshopifyapp.features.users.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.internal.Classes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.weshopifyapp.features.users.WeshopifyUsermanagementServiceApplication;
import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

@SpringBootTest(classes = WeshopifyUsermanagementServiceApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class PermissionsImplTest {
	
	@Mock
	PermissionsRepository permissionsRepository;

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	PermissionsImpl permissionsImpl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreatePermission() throws PermissionsNotFoundException {
		PermissionsBean permissionsBean = PermissionsBean.builder().name("Create").build();
		PermissionsBean permissionsBean2 = PermissionsBean.builder().name("Create").permissionId(1).build();
		Permissions permissions = Permissions.builder().name("Create").permissionId(1).build();
		when(permissionsRepository.save(permissions)).thenReturn(permissions);
		when(modelMapper.map(permissionsBean, Permissions.class)).thenReturn(permissions);
		when(modelMapper.map(permissions, PermissionsBean.class)).thenReturn(permissionsBean2);
		permissionsBean = permissionsImpl.createPermission(permissionsBean);
		verify(permissionsRepository, Mockito.times(1)).save(permissions);// We are verifying is really the mocked
																			// object method is called or not
//		verify(modelMapper,Mockito.times(1)).map(permissionsBean, Permissions.class);
//		verify(modelMapper,Mockito.times(1)).map(permissions, PermissionsBean.class);
		assertEquals(permissions.getPermissionId(), permissionsBean.getPermissionId());
		assertEquals(permissions.getName(), permissionsBean.getName());
	}

	@Test
	void testUpdatePermission() throws PermissionsNotFoundException {
		PermissionsBean permissionsBean = PermissionsBean.builder().name("View").permissionId(1).build();
		Permissions permissions = Permissions.builder().name("View").permissionId(1).build();
		when(permissionsRepository.save(permissions)).thenReturn(permissions);
		when(modelMapper.map(permissionsBean, Permissions.class)).thenReturn(permissions);
		when(modelMapper.map(permissions, PermissionsBean.class)).thenReturn(permissionsBean);
		permissionsBean = permissionsImpl.updatePermission(permissionsBean);
		verify(permissionsRepository, Mockito.times(1)).save(permissions);// We are verifying is really the mocked
																			// object method is called or not
//		verify(modelMapper,Mockito.times(1)).map(permissionsBean, Permissions.class);
//		verify(modelMapper,Mockito.times(1)).map(permissions, PermissionsBean.class);
		assertEquals(permissions.getPermissionId(), permissionsBean.getPermissionId());
		assertEquals(permissions.getName(), permissionsBean.getName());
	}
//
//	@Test
//	void testFindPermissionById() {
//		
//	}
//
//	@Test
//	void testDeletePermissionById() {
//		
//	}
//
//	@Test
//	void testGetAllPermissions() {
//		
//	}
//
//	@Test
//	void testSearchPermissions() {
//		
//	}

}
