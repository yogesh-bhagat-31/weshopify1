package com.weshopifyapp.features.categories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.weshopifyapp.features.categories.config.UserAuthzService;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeshopifyPlatformCategoriesServiceApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class UserAuthzTest {

	@Autowired
	private UserAuthzService userAuthzService;


	private HttpServletRequest request;

	@BeforeEach
	public void setUp() {
		assertNotNull(userAuthzService);
		assertNotNull(request);
		String token = "eyJ4NXQiOiJNREpsTmpJeE4yRTFPR1psT0dWbU1HUXhPVEZsTXpCbU5tRmpaalEwWTJZd09HWTBOMkkwWXpFNFl6WmpOalJoWW1SbU1tUTBPRGRpTkRoak1HRXdNQSIsImtpZCI6Ik1ESmxOakl4TjJFMU9HWmxPR1ZtTUdReE9URmxNekJtTm1GalpqUTBZMll3T0dZME4ySTBZekU0WXpaak5qUmhZbVJtTW1RME9EZGlORGhqTUdFd01BX1JTMjU2IiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJkYTY5NDc3My01YzdlLTQwYzQtODQyMC1mMmExOWM3MjgzYTQiLCJhdXQiOiJBUFBMSUNBVElPTl9VU0VSIiwiYXVkIjoiX0wxdUNka3pFckM3TGFNUHNjUlFvZmVfblY0YSIsIm5iZiI6MTcxMTI1OTE5NiwiYXpwIjoiX0wxdUNka3pFckM3TGFNUHNjUlFvZmVfblY0YSIsInNjb3BlIjoib3BlbmlkIiwiaXNzIjoiaHR0cHM6XC9cL2xvY2FsaG9zdDo5NDQzXC9vYXV0aDJcL3Rva2VuIiwiZXhwIjoxNzExMjYyNzk2LCJpYXQiOjE3MTEyNTkxOTYsImp0aSI6Ijc1MTUwZTBiLTg1MzEtNDExNy1hYzVhLTY5ZWIxM2I5OTUyYiIsImNsaWVudF9pZCI6Il9MMXVDZGt6RXJDN0xhTVBzY1JRb2ZlX25WNGEifQ.K6x69m7Hld4Z7aVhfS0FxZmNQzjELcHmXZ4H9Iq6d0yc9Td2RoUZrOUvTiwv_8sZLAm4p32uwNfWsyOQ78J878sB6-Vgoc3nUmr6xOp3_j0njWI1x1jC07gs5OKVPWl041JoVdTw3W9RPtk4rdt430P8y36Oj1MRSC7mfmJ7ewaQGLhu2LeImSTApipcTyFPjRavV4JSVgmBMmMDS2Tst3BdRHazz6cKwQjndzSVl1lxETQL1y8fxHeThw8itCOeHM1PiK6-B4C94_lJObScNIFD8aII0IT1AmrgQ0lZpsTsTGSTXUBWkbddJXn5L070Q_wSeH0iRPKm9f7NwKgiNw";
		MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		mockRequest.addHeader("Authorization", "Bearer " + token);
	}

	@Test
	public void testAuthorization() {
		Authentication authentication = userAuthzService.authorizeToken(request);
		assertNotNull(authentication);
		assertTrue(authentication.isAuthenticated());
	}

}
