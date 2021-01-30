package com.jasper.HouseAPI.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HouseResourceServiceTest {

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

}
