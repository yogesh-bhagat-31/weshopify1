package com.weshopifyapp.features.categories.exceptions;


import java.util.Date;

public class CategoriesNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5353682021610172811L;

	public CategoriesNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String errorMessage;
	private int errorCode;

	private Date timeStamp;

}
