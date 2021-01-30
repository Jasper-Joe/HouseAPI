package com.jasper.HouseAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HouseNotFoundException extends RuntimeException {
	public HouseNotFoundException(String message) {
		super(message);
	}

}
