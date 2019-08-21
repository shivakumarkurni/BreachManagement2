package com.breach.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/* breachEngine making breach and risk calculation
 * author sairam
 * @param  franchiseId int
 * @param  bussinessId int
 * @param  categoryId int
 * @param   userId
 *  */
import com.breach.dto.LoginDto;
import com.breach.dto.RiskDto;
import com.breach.dto.UserDetailsDto;
import com.breach.service.BreachService;
import org.springframework.web.bind.annotation.GetMapping;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;
import com.breach.service.BranchService;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class BreachController {
	 
	@Autowired 
	BranchService branchService;
	
	@Autowired
	private BreachService breachService;
	

	final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);

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
	
	/***
	 * Created login method where user is allowed to login with name and password 
	 * based on user type.
	 * @param loginDto
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<UserDetailsDto> login(@RequestBody LoginDto loginDto) {
		
		LOGGER.info("BreachController :: loginDto :{} ",loginDto);
		return new ResponseEntity<>(breachService.login(loginDto), HttpStatus.OK);

	}
}



