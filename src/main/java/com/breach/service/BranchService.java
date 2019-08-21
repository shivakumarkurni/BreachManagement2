package com.breach.service;

import org.springframework.http.ResponseEntity;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;

public interface BranchService {

	public ResponseEntity<ResponseDto> breachEngine(BreachEngineInput breachEngineInput);
	
	public void getFranchise();
	public void getBussinessId();
	public void getCategoryIdId();


	
}
