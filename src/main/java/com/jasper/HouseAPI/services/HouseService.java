package com.jasper.HouseAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasper.HouseAPI.domain.House;
import com.jasper.HouseAPI.exceptions.HouseCreationException;
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
		return houseRepository.findHouseById(id);
		//return houseRepository.findById(id);
	}

}
