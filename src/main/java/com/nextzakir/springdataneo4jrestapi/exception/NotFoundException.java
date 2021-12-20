package com.nextzakir.springdataneo4jrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2927762559336817363L;
	
	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
