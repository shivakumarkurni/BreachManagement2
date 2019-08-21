package com.breach.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;
import com.breach.service.BranchService;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class BreachController {
	 
	@Autowired BranchService branchService;

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
	
}



