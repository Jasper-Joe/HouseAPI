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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.HouseAPI.domain.House;
import com.jasper.HouseAPI.exceptions.HouseCreationException;
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

		houseService.saveHouse(house);
		return new ResponseEntity<House>(house, HttpStatus.CREATED);
	}
	
	@GetMapping("/{houseId}")
	public ResponseEntity<?> getHouseById(@PathVariable String houseId) {
		Long id = houseService.stringToLong(houseId);
		
		if(id == null || id <= 0) {
			return new ResponseEntity<String>("House Id is invalid", HttpStatus.BAD_REQUEST);
		}
		
		House house = houseService.findHouseById(id);
		
		if(house == null) {
			// House Id is valid, but cannot find according house resource
			return new ResponseEntity<String>("House with ID: " + houseId + " does not exist.", HttpStatus.OK);
		}
		
		return new ResponseEntity<House>(house, HttpStatus.OK);
	}
	
	
	@GetMapping("")
	public ResponseEntity<?> getAllHouses() {
		return houseService.findAllHouses();
	}
	
	@DeleteMapping("/{houseId}")
	public ResponseEntity<?> deleteHouse(@PathVariable String houseId) {
		Long id = houseService.stringToLong(houseId);
		if(id == null) {
			return new ResponseEntity<String>("House Id is invalid", HttpStatus.BAD_REQUEST);
		}
		
		houseService.deleteHouseById(id);
		return new ResponseEntity<String>("House with ID: " + houseId + " was deleted", HttpStatus.OK);
	}
	
	
	@PutMapping("/{houseId}")
	public ResponseEntity<?> updateHouse(@RequestBody House house, @PathVariable String houseId, BindingResult result) {
		Long id = houseService.stringToLong(houseId);
		if(id == null || id <= 0) {
			return new ResponseEntity<String>("House Id is invalid", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity<?> errorMap = validationService.MapValidationService(result);
		if(errorMap != null) { // got an invalid object
			return errorMap;
		}

		House previouseHouse = houseService.findHouseById(id);
		if(previouseHouse == null) {
			return new ResponseEntity<String>("House with ID: " + houseId + " does not exist.", HttpStatus.BAD_REQUEST);
		}
		try {
			House updatedHouse = houseService.updateHouse(house, id);
			return new ResponseEntity<House>(updatedHouse, HttpStatus.CREATED);
		} catch(InvalidInputException e) {
			return new ResponseEntity<String>("All fields are required", HttpStatus.BAD_REQUEST);
		}
	}

}
