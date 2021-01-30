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

import com.jasper.HouseAPI.domain.HouseResource;
import com.jasper.HouseAPI.exceptions.HouseCreationException;
import com.jasper.HouseAPI.exceptions.HouseIdException;
import com.jasper.HouseAPI.exceptions.HouseNotFoundException;
import com.jasper.HouseAPI.exceptions.InvalidInputException;
import com.jasper.HouseAPI.repositories.HouseResourceRepository;

/**
 * HouseResourceService class is used to decouple business logical from controllers
 * @author jinglunzhou
 *
 */

@Service
public class HouseResourceService {
	
	@Autowired
	private HouseResourceRepository houseRepository;
	
	// constant string(URL prefix) used to set location property
	private final String kURL = "http://localhost:8080/api/houses/";
	
	/**
	 * Save the house resource into database
	 * @param house The <code>HouseResource</code> to save
	 * @return The <code>HouseResource</code> just saved
	 */
	public HouseResource saveHouse(HouseResource house) {
		try {
			// nextId represents the current HouseResource's ID
			Long nextId = numOfRows() + 1;
			
			// Set location property before saving into database
			house.setLocation(kURL + nextId);
			return houseRepository.save(house);
		} catch(Exception e) {
			throw new HouseCreationException("House ID " + house.getId() + "cannot be created.");
		}
	}
	
	public HouseResource findHouseById(Long id) {
		HouseResource house = houseRepository.findHouseResourceById(id);
		return house;
	}
	
	public HouseResource updateHouse(HouseResource house, Long id) {
		HouseResource existingHouse = houseRepository.findHouseResourceById(id);
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
		Iterable<HouseResource> res = houseRepository.findAll();
		Long count = numOfRows();
		map.put("itemCount", count);
		map.put("items", res);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	public void deleteHouseById(Long id) {
		HouseResource house = houseRepository.findHouseResourceById(id);
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
	
	/**
	 * Read initial data from CSV file
	 * Only used once when the application starts
	 */
	public void extractFileData() {
		
		// A flag used to filter out the file header
		boolean isHeader = true;
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/houses.csv"));
			while((line = br.readLine()) != null) {
				
				// If isHeader is true, meaning this is file header, ignore
				if(isHeader) {
					isHeader = false;
					continue;
				}
				String[] data = line.split(",");
				HouseResource house = new HouseResource();
				house.setFirstName(data[1]);
				house.setLastName(data[2]);
				house.setStreet(data[3]);
				house.setCity(data[4]);
				house.setState(data[5]);
				house.setZip(data[6]);
				house.setPropertyType(data[7]);
				Long nextId = numOfRows() + 1;
				
				// concatenate URL prefix with ID to set location property
				house.setLocation(kURL + nextId);
				houseRepository.save(house);
			}
			
			// For security reason, after reading the file, close it!
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
