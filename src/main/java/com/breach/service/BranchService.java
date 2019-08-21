package com.breach.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.DataDragAndDrop;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;

public interface BranchService {

	public ResponseEntity<ResponseDto> breachEngine(BreachEngineInput breachEngineInput);
	public ResponseEntity<RiskChecking> riskCheck(BreachEngineInput breachEngineInput);
	
	public ResponseEntity<List<DataDragAndDrop>> franchise();
	public ResponseEntity<List<DataDragAndDrop>> bussiness(Integer franchiseId);
	public ResponseEntity<List<DataDragAndDrop>> category();
	public ResponseEntity<List<DataDragAndDrop>> identifiedBreach();

}
