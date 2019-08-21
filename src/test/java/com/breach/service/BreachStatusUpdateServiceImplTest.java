package com.breach.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.breach.dto.RequestDTO;
import com.breach.dto.ResponseDto;
import com.breach.entity.Breach;
import com.breach.exception.BreachNotFound;
import com.breach.repository.BreachStatusUpdateRepository;

@RunWith(MockitoJUnitRunner.class)
public class BreachStatusUpdateServiceImplTest {

	@Mock
	BreachStatusUpdateRepository breachStatusUpdateRepository;

	@InjectMocks
	BreachStatusUpdateServiceImpl breachStatusUpdateServiceImpl;

	@Test
	public void testBreachUpdate() {

		RequestDTO requestDTO = new RequestDTO();

		requestDTO.setUserId(1);
		requestDTO.setBreachId(1);
		requestDTO.setAction("closed");

		ResponseDto responseDto = new ResponseDto();

		responseDto.setMessage("status updated successfully");
		responseDto.setStatusCode(201);

		Breach breach = new Breach();

		breach.setBreachId(1);
		breach.setBusinessId(1);
		breach.setCategoryId(1);
		breach.setCreatedDate(LocalDateTime.now());
		breach.setFranchiseId(1);
		breach.setRisk("HIGH");
		breach.setStatus("open");
		breach.setUserId(1);

		Mockito.when(breachStatusUpdateRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(breach));

		// breach.setStatus("closed");

		// Mockito.when(breachStatusUpdateRepository.save(Mockito.any())).thenReturn(breach);

		ResponseDto actualValue = breachStatusUpdateServiceImpl.breachUpdate(requestDTO);

		assertEquals(responseDto.getMessage(), actualValue.getMessage());

	}

	@Test(expected = BreachNotFound.class)
	public void testBreachUpdateNegitive() {

		RequestDTO requestDTO = new RequestDTO();

		requestDTO.setUserId(1);
		requestDTO.setBreachId(1);
		requestDTO.setAction("closed");

		ResponseDto responseDto = new ResponseDto();

		responseDto.setMessage("status updated successfully");
		responseDto.setStatusCode(201);

		Breach breach = new Breach();

		breach.setBreachId(1);
		breach.setBusinessId(1);
		breach.setCategoryId(1);
		breach.setCreatedDate(LocalDateTime.now());
		breach.setFranchiseId(1);
		breach.setRisk("HIGH");
		breach.setStatus("open");
		breach.setUserId(1);

		

		breach.setStatus("closed");

		//Mockito.when(breachStatusUpdateRepository.save(Mockito.any())).thenReturn(breach);

		ResponseDto actualValue = breachStatusUpdateServiceImpl.breachUpdate(requestDTO);

	}

}
