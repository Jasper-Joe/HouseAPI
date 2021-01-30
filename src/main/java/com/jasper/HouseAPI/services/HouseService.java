package com.jasper.HouseAPI.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	
	private String kURL = "http://localhost:8080/api/houses/";
	
	
	
	public House saveHouse(House house) {
		try {
			Long nextId = numOfRows() + 1;
			house.setLocation(kURL + nextId);
			return houseRepository.save(house);
		} catch(Exception e) {
			throw new HouseCreationException("House ID " + house.getId() + "cannot be created.");
		}
	}
	
	public void extractFileData() {
		boolean isHeader = true;
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/houses.csv"));
			while((line = br.readLine()) != null) {
				if(isHeader) { // Filter out file header
					isHeader = false;
					continue;
				}
				String[] data = line.split(",");
				House house = new House();
				house.setFirstName(data[1]);
				house.setLastName(data[2]);
				house.setStreet(data[3]);
				house.setCity(data[4]);
				house.setState(data[5]);
				house.setZip(data[6]);
				house.setPropertyType(data[7]);
				Long nextId = numOfRows() + 1;
				house.setLocation(kURL + nextId);
				houseRepository.save(house);
			}
		} catch(IOException e) {
			e.printStackTrace();
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
		Long count = numOfRows();
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
	
	private Long numOfRows() {
		return houseRepository.count();
	}
	

}
