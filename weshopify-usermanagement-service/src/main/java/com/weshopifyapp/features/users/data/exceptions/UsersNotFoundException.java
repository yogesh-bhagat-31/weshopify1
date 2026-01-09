package com.weshopifyapp.features.users.data.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class UsersNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1456277848879452969L;
	private String message;

}
