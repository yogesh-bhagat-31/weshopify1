package com.weshopifyapp.features.authn.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wso2TokenRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4534803424209491551L;
		
	private String grant_type;
	private String username;
	private String password;
	private String scope;
		

}
