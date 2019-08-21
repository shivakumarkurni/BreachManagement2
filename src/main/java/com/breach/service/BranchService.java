package com.breach.service;

import org.springframework.http.ResponseEntity;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;

public interface BranchService {

	public ResponseEntity<ResponseDto> breachEngine(BreachEngineInput breachEngineInput);
//	public ResponseEntity<RiskChecking> riskCheck(BreachEngineInput breachEngineInput);

	
	
	
}
