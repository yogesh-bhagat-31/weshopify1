package com.weshopifyapp.features.brands.model;

import java.io.Serializable;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Student")
@Data
public class Brands implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5755480887549275297L;
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private Set<String> categories;

}
