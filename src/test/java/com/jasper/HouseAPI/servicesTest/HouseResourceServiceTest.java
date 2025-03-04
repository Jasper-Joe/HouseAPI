package com.jasper.HouseAPI.servicesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.runner.RunWith;

import com.jasper.HouseAPI.domain.HouseResource;
import com.jasper.HouseAPI.repositories.HouseResourceRepository;
import com.jasper.HouseAPI.services.HouseResourceService;


@SpringBootTest
@AutoConfigureMockMvc
class HouseResourceServiceTest {

	@Autowired
	private HouseResourceService houseResourceService;
	
	@Autowired
	HouseResourceRepository houseResourceRepository;
	
	@MockBean
	private HouseResourceRepository rep;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testStringToLong() {
		HouseResourceService houseResourceService = new HouseResourceService();
		// General case
		Long res1 = houseResourceService.stringToLong("123");
		
		// Negative number
		Long res2 = houseResourceService.stringToLong("-1");
		
		assertEquals(123,res1);
		assertEquals(-1,res2);
	}
	
	@Test
	public void testCreateHouseResource() {
		
		HouseResource house = new HouseResource();
		house.setCity("Medford");
		house.setFirstName("Jasper");
		house.setLastName("Zhou");
		house.setZip("02155");
		house.setPropertyType("Multi Family");
		house.setState("MA");
		house.setStreet("Pearl St");
		
		Mockito.when(rep.save(house)).thenReturn(house);
		assertThat(houseResourceService.saveHouse(house)).isEqualTo(house);
	}
	
	@Test
	public void testGetById() {
		HouseResource house = new HouseResource();
		house.setCity("Medford");
		house.setFirstName("Jasper");
		house.setLastName("Zhou");
		house.setZip("02155");
		house.setPropertyType("Multi Family");
		house.setState("MA");
		house.setStreet("Pearl St");
		Mockito.when(rep.findHouseResourceById((long) 1)).thenReturn(house);
		assertThat(houseResourceService.findHouseById((long) 1)).isEqualTo(house);
	}

}
