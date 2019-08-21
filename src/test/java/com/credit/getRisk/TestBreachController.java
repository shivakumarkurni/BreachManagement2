package com.credit.getRisk;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.breach.controller.BreachController;
import com.breach.service.BreachServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestBreachController {

	@Mock
	private BreachServiceImpl breachServiceImpl;
	
	@InjectMocks
	private BreachController breachController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(breachController).build();
	}
	
	@Test
	public void testBreachRisk() throws Exception {
		mockMvc.perform(get("/risk/{roleType}","HIGH").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
