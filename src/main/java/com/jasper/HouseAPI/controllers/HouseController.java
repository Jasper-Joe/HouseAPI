package com.jasper.HouseAPI.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.HouseAPI.domain.House;
import com.jasper.HouseAPI.services.HouseService;

@RestController
@RequestMapping("/api/houses")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewHouse(@Valid @RequestBody House house, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		House newHouse = houseService.saveOrUpdateHouse(house);
		return new ResponseEntity<House>(house, HttpStatus.CREATED);
	}

}
