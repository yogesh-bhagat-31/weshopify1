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
public class PermissionsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6321266537930398977L;
	private String message;

}
