package com.weshopifyapp.features.categories.validations;

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
public class CategoriesValidationsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -148754188964254344L;
	private String errorMessage;
	private int errorCode;

	private Date timeStamp;

//	@Override
//	public synchronized Throwable fillInStackTrace() {
//		// TODO Auto-generated method stub
//		return this;
//	}

//	@Override
//	public StackTraceElement[] getStackTrace() {
//		// TODO Auto-generated method stub
//		return  new StackTraceElement[0];
//	}

}
