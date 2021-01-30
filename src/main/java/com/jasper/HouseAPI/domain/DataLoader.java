package com.jasper.HouseAPI.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jasper.HouseAPI.services.HouseResourceService;

/**
 * DataLoader class is used to extract initial data from CSV file
 * @author jinglunzhou
 *
 */
@Component
public class DataLoader {
	
	private HouseResourceService houseService;
	
	@Autowired
	public DataLoader(HouseResourceService houseService) {
		this.houseService = houseService;
	}
	
	/**
	 * Load initial data from CSV file when the application starts
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		houseService.extractFileData();
	}
	


}
