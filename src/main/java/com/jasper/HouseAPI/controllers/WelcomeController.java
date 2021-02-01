package com.jasper.HouseAPI.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home page router
 * @author jinglunzhou
 *
 */
@Controller
public class WelcomeController {
	
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		String message = "Welcome to VISION GOVERNMENT SOLUTIONS.";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
