package com.weshopifyapp.features.users.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weshopifyapp.features.users.data.models.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Integer> {
	
	

}
