package com.weshopifyapp.features.categories.exceptions;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3536733888296144219L;

	private String errorMessage;
	private int errorCode;

	private Date timeStamp;

}
