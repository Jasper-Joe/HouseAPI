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

import com.jasper.HouseAPI.domain.HouseResource;
import com.jasper.HouseAPI.exceptions.HouseCreationException;
import com.jasper.HouseAPI.exceptions.InvalidInputException;
import com.jasper.HouseAPI.services.HouseResourceService;
import com.jasper.HouseAPI.services.ValidationService;

/**
 * Handle the HTTP requests
 * @author jinglunzhou
 *
 */
@RestController
@RequestMapping("/api/houses")
public class HouseController {
	
	@Autowired
	private HouseResourceService houseService;
	
	@Autowired
	private ValidationService validationService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewHouse(@Valid @RequestBody HouseResource house, BindingResult result) {
		
		ResponseEntity<?> errorMap = validationService.MapValidationService(result);
		// If we get an invalid input object, return error message
		if(errorMap != null) { 
			return errorMap;
		}

		houseService.saveHouse(house);
		return new ResponseEntity<HouseResource>(house, HttpStatus.CREATED);
	}
	
	@GetMapping("/{houseId}")
	public ResponseEntity<?> getHouseById(@PathVariable String houseId) {
		Long id = houseService.stringToLong(houseId);
		
		// If input ID is invalid
		if(id == null || id <= 0) {
			return new ResponseEntity<String>("House Id is invalid", HttpStatus.BAD_REQUEST);
		}
		
		HouseResource house = houseService.findHouseById(id);
		
		// If the house does not exist 
		if(house == null) {
			// House Id is valid, but cannot find according house resource
			return new ResponseEntity<String>("House with ID: " + houseId + " does not exist.", HttpStatus.OK);
		}
		
		return new ResponseEntity<HouseResource>(house, HttpStatus.OK);
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
	public ResponseEntity<?> updateHouse(@RequestBody HouseResource house, @PathVariable String houseId, BindingResult result) {
		Long id = houseService.stringToLong(houseId);
		if(id == null || id <= 0) {
			return new ResponseEntity<String>("House Id is invalid", HttpStatus.BAD_REQUEST);
		}
		
		ResponseEntity<?> errorMap = validationService.MapValidationService(result);
		
		// If we get an invalid input object, return error message
		if(errorMap != null) { 
			return errorMap;
		}

		HouseResource previouseHouse = houseService.findHouseById(id);
		
		// If the house to update does not exist
		if(previouseHouse == null) {
			return new ResponseEntity<String>("House with ID: " + houseId + " does not exist.", HttpStatus.BAD_REQUEST);
		}
		try {
			HouseResource updatedHouse = houseService.updateHouse(house, id);
			return new ResponseEntity<HouseResource>(updatedHouse, HttpStatus.CREATED);
		} catch(InvalidInputException e) {
			return new ResponseEntity<String>("All fields are required", HttpStatus.BAD_REQUEST);
		}
	}

}
