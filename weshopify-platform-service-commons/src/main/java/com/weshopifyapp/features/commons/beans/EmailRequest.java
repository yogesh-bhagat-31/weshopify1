package com.weshopifyapp.features.commons.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5242736585784833952L;
	
	private String email;
	private String userId;
	private int id;

}
