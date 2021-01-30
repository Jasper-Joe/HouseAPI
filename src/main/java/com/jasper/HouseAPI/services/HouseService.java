package com.jasper.HouseAPI.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jasper.HouseAPI.domain.House;
import com.jasper.HouseAPI.exceptions.HouseCreationException;
import com.jasper.HouseAPI.exceptions.HouseIdException;
import com.jasper.HouseAPI.exceptions.HouseNotFoundException;
import com.jasper.HouseAPI.exceptions.InvalidInputException;
import com.jasper.HouseAPI.repositories.HouseRepository;

@Service
public class HouseService {
	
	// handle business logic for controllers
	
	@Autowired
	private HouseRepository houseRepository;
	
	public House saveHouse(House house) {
		try {
			return houseRepository.save(house);
		} catch(Exception e) {
			throw new HouseCreationException("House ID " + house.getId() + "cannot be created.");
		}
	}
	
	public House findHouseById(Long id) {
		House house = houseRepository.findHouseById(id);
		return house;
	}
	
	public House updateHouse(House house, Long id) {
		House existingHouse = houseRepository.findHouseById(id);
		if(existingHouse == null) {
			throw new HouseNotFoundException("This cannot be updated, because it does not exist");
		}
		if(house.getCity() == null || house.getFirstName() == null || house.getLastName() == null || house.getStreet() == null || house.getZip() == null || house.getPropertyType() == null) {
			throw new InvalidInputException("All fields are required");
		}
		existingHouse.setCity(house.getCity());
		existingHouse.setFirstName(house.getFirstName());
		existingHouse.setLastName(house.getLastName());
		existingHouse.setStreet(house.getStreet());
		existingHouse.setZip(house.getZip());
		existingHouse.setPropertyType(house.getPropertyType());
		return houseRepository.save(existingHouse);
	}
	
	public ResponseEntity<?> findAllHouses() {
		Map<String, Object> map = new LinkedHashMap<>();
		Iterable<House> res = houseRepository.findAll();
		int count = 0;
		for(House house : res) {
			count++;
		}
		map.put("itemCount", count);
		map.put("items", res);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	public void deleteHouseById(Long id) {
		House house = houseRepository.findHouseById(id);
		if(house == null) {
			throw new HouseIdException("This house id does not exist");
		}
		
		houseRepository.delete(house);
	}
	
	public Long stringToLong(String houseId) {
		Long id;
		try {
			id = Long.parseLong(houseId);
		} catch (NumberFormatException n) {
			return null;
		}
		return id;
	}
	

}
