package com.breach.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.breach.dto.RiskDto;
import com.breach.service.BreachService;

@RestController
@RequestMapping("/breach")
public class BreachController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);
	
	@Autowired
	private BreachService breachService;
	
	@RequestMapping("/risk")
	public ResponseEntity<List<RiskDto>> breachRisk(@PathVariable String roleType){
		
		LOGGER.info("BreachController :: ");
		return new ResponseEntity<>(breachService.getBreachRisk(roleType),HttpStatus.OK);
	}
}
