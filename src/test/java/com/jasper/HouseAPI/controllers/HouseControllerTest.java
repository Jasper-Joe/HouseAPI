package com.jasper.HouseAPI.controllers;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HouseController.class)
class HouseControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		
	}
	
	@Test
	void getAllHouses() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/");
		MvcResult result = mvc.perform(request).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		//assertEquals("", result.getResponse().getContentAsString());
		
	}

}
