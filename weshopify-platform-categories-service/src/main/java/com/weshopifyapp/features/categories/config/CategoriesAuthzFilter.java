package com.weshopifyapp.features.categories.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;


/**
 * This GenericFilterBean class, GenericFilterBean, serves as a foundation or
 * template for creating filters in Java web applications, particularly those
 * using the Jakarta Servlet API.
 * 
 * @author Yogesh
 * The below custome filter we will put just before the Basic Authn filter 
 */
public class CategoriesAuthzFilter extends GenericFilterBean {

	UserAuthzService userAuthzService;

	public CategoriesAuthzFilter(UserAuthzService userAuthzService) {
		super();
		this.userAuthzService = userAuthzService;
	}

	/**
	 * It's like a gatekeeper that can inspect and tweak things before they reach
	 * the main part of the web application.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * Below authentication object contains Username and credentials and roles also
		 * inside it
		 */
		Authentication authentication = userAuthzService.authorizeToken((HttpServletRequest) request);

		/**
		 * Here if user is already authenticated we will put isAuthenticated inside
		 * SecurityContextHolder
		 */
		if (authentication != null && authentication.isAuthenticated()) {
            /**
             * Below SecurityContextHolder setting is very imp for the next
             * Basic authn filter of spring security.This filter will lookup 
             * the isAuthenticated true or not witihin the authn object
             * which is inside the secuirty context holder
             */
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} else {
           System.out.println("User is not authorised");
		}

	}

}
