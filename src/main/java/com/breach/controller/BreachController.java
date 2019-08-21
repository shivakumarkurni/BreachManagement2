package com.breach.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;
import com.breach.dto.RiskDto;
import com.breach.service.BranchService;
import com.breach.service.BreachService;


@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class BreachController {
	 
	@Autowired BranchService branchService;
	@Autowired
	private BreachService breachService;
	

	final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);

	
	/* breachEngine making breach and risk calculation
	 * author sairam
	 * @param  franchiseId int
	 * @param  bussinessId int
	 * @param  categoryId int
	 * @param   userId
	 *  */
	@PostMapping("/")
	public ResponseEntity<ResponseDto> breachEngine(@RequestBody BreachEngineInput breachEngineInput) {
		return branchService.breachEngine(breachEngineInput);
		
		
	}
	
	@PostMapping("/risk/check")
	public ResponseEntity<RiskChecking> riskCheck(@RequestBody  BreachEngineInput breachEngineInput) {
		return branchService.riskCheck(breachEngineInput);
		
	}

	
	
	
	/**
	 * @param roleType
	 * @return
	 */
	@GetMapping("/risk/{roleType}")
	public ResponseEntity<List<RiskDto>> breachRisk(@PathVariable String roleType){
		
		LOGGER.info("BreachController :: roleType :{} ",roleType);
		return new ResponseEntity<>(breachService.getBreachRisk(roleType),HttpStatus.OK);
	}
}



