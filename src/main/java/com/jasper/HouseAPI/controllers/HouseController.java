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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.HouseAPI.domain.House;
import com.jasper.HouseAPI.exceptions.InvalidInputException;
import com.jasper.HouseAPI.services.HouseService;
import com.jasper.HouseAPI.services.ValidationService;

@RestController
@RequestMapping("/api/houses")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private ValidationService validationService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewHouse(@Valid @RequestBody House house, BindingResult result) {
		
		ResponseEntity<?> errorMap = validationService.MapValidationService(result);
		if(errorMap != null) { // got an invalid object
			return errorMap;
		}

		House newHouse = houseService.saveOrUpdateHouse(house);
		return new ResponseEntity<House>(house, HttpStatus.CREATED);
	}
	
	@GetMapping("/{houseId}")
	public ResponseEntity<?> getHouseById(@PathVariable String houseId) {
		Long id;
		try {
			id = Long.parseLong(houseId);
		} catch (NumberFormatException n) {
			throw new InvalidInputException("Cannot convert to long");
		}
		House house = houseService.findHouseById(id);
		return new ResponseEntity<House>(house, HttpStatus.OK);
	}
	
//	@GetMapping("")
//	public Iterable<House> getAllHouses() {
//		return houseService.findAllHouses();
//	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllHouses() {
		return houseService.findAllHouses();
	}

}
