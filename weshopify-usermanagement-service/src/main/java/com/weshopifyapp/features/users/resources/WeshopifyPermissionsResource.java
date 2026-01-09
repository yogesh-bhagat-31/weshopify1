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

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.services.PermissionsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class WeshopifyPermissionsResource {

	private PermissionsService permissionsService;

	public WeshopifyPermissionsResource(PermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	@PostMapping("/permissions")
	public ResponseEntity<PermissionsBean> createPermissions(@RequestBody PermissionsBean permissionsBean) {
		log.info("Permission data is:\t" + permissionsBean.toString());
		permissionsBean = permissionsService.createPermission(permissionsBean);
		return ResponseEntity.ok(permissionsBean);

	}
	
	@PutMapping("/permissions")
	public ResponseEntity<PermissionsBean> updatePermissions(@RequestBody PermissionsBean permissionsBean) {
		log.info("Permission data is:\t" + permissionsBean.toString());
		permissionsBean = permissionsService.updatePermission(permissionsBean);
		return ResponseEntity.ok(permissionsBean);	
	}
	
	
	@GetMapping("/permissions")
	public ResponseEntity<List<PermissionsBean>> getAllPermissions() {
		return ResponseEntity.ok(permissionsService.getAllPermissions());	
	}
	
	
	@GetMapping("/permissions/{permissionsId}")
	public ResponseEntity<PermissionsBean> getPermissionsById(@PathVariable("permissionsId") int permissionsId) {
		return ResponseEntity.ok(permissionsService.findPermissionById(permissionsId));	
	}
	
	
	@DeleteMapping("/permissions/{permissionsId}")
	public ResponseEntity<List<PermissionsBean>> deletePermissionsById(@PathVariable("permissionsId") int permissionsId) {
		return ResponseEntity.ok(permissionsService.deletePermissionById(permissionsId));	
	}
	
	

}
