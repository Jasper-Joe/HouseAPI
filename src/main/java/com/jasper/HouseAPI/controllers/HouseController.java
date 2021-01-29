package com.jasper.HouseAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<House> createNewHouse(@RequestBody House house) {
		House newHouse = houseService.saveOrUpdateHouse(house);
		return new ResponseEntity<House>(house, HttpStatus.CREATED);
	}

}
