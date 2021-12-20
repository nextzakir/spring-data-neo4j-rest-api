package com.nextzakir.springdataneo4jrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
	private static final long serialVersionUID = -2927762559336817363L;
	
	public UnprocessableEntityException(String errorMessage) {
		super(errorMessage);
	}
}
