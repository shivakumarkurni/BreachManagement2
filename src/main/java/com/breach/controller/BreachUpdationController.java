package com.breach.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.breach.dto.RequestDTO;
import com.breach.dto.ResponseDto;
import com.breach.service.BreachStatusUpdateServiceImpl;

/**
 * @author Shiva
 *
 */

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class BreachUpdationController {

	private static final Logger logger = LoggerFactory.getLogger(BreachUpdationController.class);

	@Autowired
	BreachStatusUpdateServiceImpl breachStatusUpdateService;

	/**
	 * This method update the status of the breach like open/close/re-open
	 * 
	 * @param RequestDTO it contains userId,breachId,status
	 * 
	 * @return ResponseDto it contains message and status code
	 *
	 */

	@PutMapping("/breach/risk")
	public ResponseEntity<ResponseDto> breachStatusUpdate(@RequestBody RequestDTO requestDTO) {

		logger.info("Inside BreachUpdationController:");

		return new ResponseEntity<>(breachStatusUpdateService.breachUpdate(requestDTO), HttpStatus.OK);

	}

}
