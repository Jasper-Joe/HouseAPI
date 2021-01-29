package com.jasper.HouseAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HouseIdException extends RuntimeException {
	public HouseIdException(String message) {
		super(message);
	}
}
