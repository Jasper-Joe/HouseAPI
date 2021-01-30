package com.jasper.HouseAPI.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jasper.HouseAPI.services.HouseResourceService;

@Component
public class DataLoader {
	private HouseResourceService houseService;
	
	@Autowired
	public DataLoader(HouseResourceService houseService) {
		this.houseService = houseService;
	}
	
	// Load initial data
	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		houseService.extractFileData();
	}
	


}
