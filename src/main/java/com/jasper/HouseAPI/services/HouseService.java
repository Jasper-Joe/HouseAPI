package com.jasper.HouseAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasper.HouseAPI.domain.House;
import com.jasper.HouseAPI.repositories.HouseRepository;

@Service
public class HouseService {
	
	// handle logic here instead of in controllers
	
	@Autowired
	private HouseRepository houseRepository;
	
	public House saveOrUpdateHouse(House house) {
		return houseRepository.save(house);
	}

}
