package com.jasper.HouseAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to VISION GOVERNMENT SOLUTIONS.";
	}

}
