package com.weshopifyapp.features.brands.bean;

import java.io.Serializable;

import java.util.Set;

import lombok.Data;

@Data
public class BrandsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5755480887549275297L;

	private String id;
	private String name;
	private Set<String> categories;

}
