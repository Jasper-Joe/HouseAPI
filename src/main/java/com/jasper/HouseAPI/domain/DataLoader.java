package com.jasper.HouseAPI.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jasper.HouseAPI.repositories.HouseRepository;
import com.jasper.HouseAPI.services.HouseService;

@Component
public class DataLoader {
	private HouseRepository houseRepository;
	private HouseService houseService;
	
	@Autowired
	public DataLoader(HouseService houseService) {
		this.houseService = houseService;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		houseService.extractFileData();
	}
	


}
