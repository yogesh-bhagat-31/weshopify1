package com.weshopifyapp.features.commons.beans;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4989181492133844369L;
	
    private List<EmailRequest> to;
    private String subject;
    private int statusCode;
}
  