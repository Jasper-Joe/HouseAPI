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
import com.jasper.HouseAPI.repositories.HouseRepository;

@Service
public class HouseService {
	
	// handle logic here instead of in controllers
	
	@Autowired
	private HouseRepository houseRepository;
	
	public House saveOrUpdateHouse(House house) {
		try {
			return houseRepository.save(house);
		} catch(Exception e) {
			throw new HouseCreationException("House ID " + house.getId() + "cannot be created.");
		}
	}
	
	public House findHouseById(Long id) {
		House house = houseRepository.findHouseById(id);
		if(house == null) {
			throw new HouseIdException("House ID " + id + " does not exist");
		}
		return house;
	}
	
//	public Iterable<House> findAllHouses() {
//		
//		return houseRepository.findAll();
//	}
	
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
	

}
