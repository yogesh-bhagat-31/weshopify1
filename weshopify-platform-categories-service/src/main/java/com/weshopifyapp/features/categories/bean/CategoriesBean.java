package com.weshopifyapp.features.categories.bean;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -605593816332602131L;

	private int id;
	
	@NotEmpty(message = "Category Name must be Provided. It should be neither be empty not blank")
	private String name;
	
	@NotEmpty(message = "Alias Name must be Provided. It should be neither be empty not blank")
	private String alias;
	private int parentCategory;

	/**
	 * image should be uploaded to the cloud and the image url should be maintained
	 * in Db
	 */
	private String image;
	private boolean isEnabled;

}
