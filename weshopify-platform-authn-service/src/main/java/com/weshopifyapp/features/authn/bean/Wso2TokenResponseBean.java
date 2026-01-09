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
public class Wso2TokenResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4983272178936250058L;

	private String access_token;
	private String refresh_token;
	private String scope;
	private String id_token;
	private String token_type;
	private long expires_in;

}
