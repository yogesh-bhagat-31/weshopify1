package com.weshopifyapp.features.categories.exceptions;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.weshopifyapp.features.categories.validations.CategoriesValidationsBean;
import com.weshopifyapp.features.categories.validations.CategoriesValidationsList;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice // This is part of AOP we are injectinh this methods in controller without touching the buisness logic
@Slf4j
public class CategoriesExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<CategoriesException> handleCategoriesException(Exception exception) {
		log.error("Categories Exception is:\t" + exception.getLocalizedMessage());
		CategoriesException catException = new CategoriesException(exception.getCause().getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catException);

	}

	@ExceptionHandler(CategoriesNotFoundException.class)
	public ResponseEntity<CategoriesException> handleCategoriesException(CategoriesNotFoundException exception) {
		log.error("Categories Exception is:\t" + exception.getLocalizedMessage());
		CategoriesException catException = new CategoriesException(exception.getCause().getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catException);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		CategoriesValidationsList exceptionsList = new CategoriesValidationsList();

		List<CategoriesValidationsBean> errorsList = new ArrayList<>();

		ex.getBindingResult().getAllErrors().stream().forEach(error -> {

			CategoriesValidationsBean fieldValidationError = new CategoriesValidationsBean(error.getDefaultMessage(),
					HttpStatus.BAD_REQUEST.value(), new Date());

			errorsList.add(fieldValidationError);
		});

		exceptionsList.setErrors(errorsList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionsList);
	}

}
