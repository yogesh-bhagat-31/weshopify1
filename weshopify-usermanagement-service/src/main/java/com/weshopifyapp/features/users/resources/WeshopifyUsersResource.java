package com.weshopifyapp.features.users.resources;

import java.util.List;
import java.util.Optional;

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
import com.weshopifyapp.features.users.beans.RolesBean;
import com.weshopifyapp.features.users.beans.UsersBean;
import com.weshopifyapp.features.users.data.exceptions.UsersNotFoundException;
import com.weshopifyapp.features.users.data.repository.UsersRepository;
import com.weshopifyapp.features.users.services.UsersService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/users")
public class WeshopifyUsersResource {

	UsersService usersService;
	UsersRepository usersRepository;

	public WeshopifyUsersResource(UsersService usersService, UsersRepository usersRepository) {
		super();
		this.usersService = usersService;
		this.usersRepository = usersRepository;
	}

	@PostMapping
	public ResponseEntity<UsersBean> createUsers(@RequestBody UsersBean usersBean) {
		log.info("Users Data is {}" + usersBean.toString());
		return ResponseEntity.ok(usersService.createUser(usersBean));
	}

	@PutMapping
	public ResponseEntity<UsersBean> updateUsers(@RequestBody UsersBean usersBean) {
		log.info("Users Data is {}" + usersBean.toString());
		return ResponseEntity.ok(usersService.updateUser(usersBean));
	}

	@GetMapping
	public ResponseEntity<List<UsersBean>> getAllUsers() {
		return ResponseEntity.ok(usersService.findAllUsers());
	}

	
	@GetMapping("{id}")
	public ResponseEntity<UsersBean> findUsersById(@PathVariable("id") int id) {
		return ResponseEntity.ok(usersService.findUserById(id));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<List<UsersBean>> deleteUsersById(@PathVariable("id") int id) {
		return ResponseEntity.ok(usersService.deleteUserById(id));
	}
	
	
	@PutMapping("/provision/{id}")
	public ResponseEntity<UsersBean> provisionUser(@PathVariable("id") int id) {
	
		return ResponseEntity.ok(usersService.provisioningRoleToUser(id));
	} 
	
	
	@GetMapping("/verifyUser/{id}")
	public ResponseEntity<UsersBean> verifyUser(@PathVariable("id") int id) {
		
		return ResponseEntity.ok(usersService.enableUser(id));
	} 
	
	
}
