package com.weshopifyapp.features.users.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.data.exceptions.RolesNotFoundException;
import com.weshopifyapp.features.users.services.RolesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class WeshopifyRolesResource {

	private RolesService rolesService;

	public WeshopifyRolesResource(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	@PostMapping("/roles")
	public ResponseEntity<RolesBean> createRoles(@RequestBody RolesBean rolesBean)  {
		log.info("Roles Data is:/t" + rolesBean.toString());
		return ResponseEntity.ok(rolesService.createRoles(rolesBean));
	}
	
	
	@PutMapping("/roles")
	public ResponseEntity<RolesBean> updateRoles(@RequestBody RolesBean rolesBean)  {
		log.info("Roles Data is:/t" + rolesBean.toString());
		return ResponseEntity.ok(rolesService.updateRoles(rolesBean));
	}
	
	
	@GetMapping("/roles/{roleId}")
	public ResponseEntity<RolesBean> findRoleById(@PathVariable int roleId)  {
		return ResponseEntity.ok(rolesService.findRoleById(roleId));
	}
	
	@DeleteMapping("/roles/{roleId}")
	public ResponseEntity<List<RolesBean>> deleteRoleById(@PathVariable int roleId)  {
		return ResponseEntity.ok(rolesService.deleteRoleById(roleId));
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<RolesBean>> getAllRoles()  {
		return ResponseEntity.ok(rolesService.getAllRoles());
	}
	
	

}
