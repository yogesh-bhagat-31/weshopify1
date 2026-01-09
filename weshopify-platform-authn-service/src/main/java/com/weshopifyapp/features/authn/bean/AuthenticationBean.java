package com.weshopifyapp.features.authn.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003611842666118693L;
	
	private String userName;
	private String password;
	

}
