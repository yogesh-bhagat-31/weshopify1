package com.weshopifyapp.features.users.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weshopifyapp.features.users.data.models.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
	
	
	@Query("from Roles roles where roles.name=:name")
	Optional<Roles> findByName(String name);

}
