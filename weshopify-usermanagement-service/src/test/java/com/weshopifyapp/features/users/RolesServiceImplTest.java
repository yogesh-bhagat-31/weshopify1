package com.weshopifyapp.features.users;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.models.RoleToPermisions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;
import com.weshopifyapp.features.users.services.RolesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class RolesServiceImplTest extends PermissionsServiceTest {
	@Autowired
	private PermissionsRepository permissions;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	RolesService rolesService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreateRole() throws RolesNotFoundException {
		List<PermissionsBean> listPerm = new ArrayList<>();
		permissions.findAll().stream().forEach((i)-> listPerm.add(modelMapper.map(i, PermissionsBean.class)));
		RolesBean rolesBean = RolesBean.builder().name("Admin").permissions(listPerm).build();
		rolesBean = rolesService.createRoles(rolesBean);
		log.info("Roles created are:t/" + rolesBean.toString());

	}

}
