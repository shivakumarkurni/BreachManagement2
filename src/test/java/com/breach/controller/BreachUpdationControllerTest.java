package com.breach.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.breach.dto.RequestDTO;
import com.breach.service.BreachStatusUpdateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class BreachUpdationControllerTest {

	@Mock
	BreachStatusUpdateServiceImpl breachStatusUpdateService;

	@InjectMocks
	BreachUpdationController breachUpdationController;

	MockMvc mockMvc;

	RequestDTO requestDTO = new RequestDTO();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(breachUpdationController).build();

		requestDTO.setUserId(1);
		requestDTO.setBreachId(1);
		requestDTO.setAction("closed");

	}

	@Test
	public void testBreachStatusUpdate() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/breach/risk").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(requestDTO))).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
