package com.jasper.HouseAPI.controllers;

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
	public String welcome() {
		return "Welcome to VISION GOVERNMENT SOLUTIONS.";
	}

}
