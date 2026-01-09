/**
 * 
 */
package com.weshopifyapp.features.users.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersBean implements Serializable {

	private static final long serialVersionUID = -305742854090098690L;

	private int id;

	private String fname;

	private String lname;

	private String email;

	private String userId;

	private String mobile;

	private boolean isEnabled;
	
	private boolean isLocked;

	private RolesBean userRoles; // Not understood
	
	private boolean isSelfReg; // Flag

}

/**
 * 1.0 When a user registers, the account is created but not yet enabled until email verification is completed.
 * 
 * {
    "fname": "Rajesh",
    "lname": "Kumar",
    "email": "rajesh.kumar@example.com",
    "userId": "user456",
    "mobile": "987-654-3210",
    "isEnabled": false,  // Not enabled until email verification
    "isLocked": false,   // Not locked, no security issues
    "isSelfReg": true
}
   2.0 After email verification, the user can log in and use the application.
   
   {
    "fname": "Rajesh",
    "lname": "Kumar",
    "email": "rajesh.kumar@example.com",
    "userId": "user456",
    "mobile": "987-654-3210",
    "isEnabled": true,   // Enabled after email verification
    "isLocked": false,   // Not locked, no security issues
    "isSelfReg": true
}


   3.0The user tries to log in but enters the wrong password multiple times. After several failed attempts,
    the account is temporarily locked to prevent unauthorized access.
    {
    "fname": "Rajesh",
    "lname": "Kumar",
    "email": "rajesh.kumar@example.com",
    "userId": "user456",
    "mobile": "987-654-3210",
    "isEnabled": true,   // Still enabled, as the account is active
    "isLocked": true,    // Locked due to multiple failed login attempts
    "isSelfReg": true
}
    
    For serious violations such as fraudulent transactions, disabling the account is the appropriate response.
   
 */


