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
import com.breach.dto.DataDragAndDrop;
import com.breach.dto.LoginDto;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;
import com.breach.dto.RiskDto;
import com.breach.dto.UserDetailsDto;
import com.breach.service.BranchService;
import com.breach.service.BreachService;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class BreachController {

	@Autowired
	BranchService branchService;

	@Autowired
	private BreachService breachService;

	final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);

	/**
	 * breachEngine making breach and risk calculation author sairam
	 * 
	 * @param franchiseId
	 *            int
	 * 
	 * @param bussinessId
	 *            int
	 * 
	 * @param categoryId
	 *            int
	 * 
	 * @param userId
	 */
	@PostMapping("/")
	public ResponseEntity<ResponseDto> breachEngine(@RequestBody BreachEngineInput breachEngineInput) {
		return branchService.breachEngine(breachEngineInput);

	}

	/**
	 * checking the risk of perticular combinaton
	 * 
	 * @param franchiseId
	 *            int
	 * 
	 * @param bussinessId
	 *            int
	 * 
	 * @param categoryId
	 *            int *
	 */
	@PostMapping("/risk/check")
	public ResponseEntity<RiskChecking> riskCheck(@RequestBody BreachEngineInput breachEngineInput) {
		LOGGER.info("BreachController --> riskCheck ");

		return branchService.riskCheck(breachEngineInput);

	}

	/**
	 * this method is to get list of breach based on user type i.e.
	 * High/low/medium/Admin
	 * 
	 * @param roleType
	 * @return
	 */
	@GetMapping("/risk/{roleType}")
	public ResponseEntity<List<RiskDto>> breachRisk(@PathVariable String roleType) {

		LOGGER.info("BreachController :: roleType :{} ", roleType);
		return new ResponseEntity<>(breachService.getBreachRisk(roleType), HttpStatus.OK);
	}

	/**
	 * 
	 * */

	@GetMapping("/franchise")
	public ResponseEntity<List<DataDragAndDrop>> franchise() {
		LOGGER.info("BreachController :: franchise");

		return branchService.franchise();

	}

	@GetMapping("/bussiness/{franchiseId}/bussiness")
	public ResponseEntity<List<DataDragAndDrop>> bussiness(@PathVariable("franchiseId") Integer franchiseId) {
		LOGGER.info("BreachController :: bussiness");

		return branchService.bussiness(franchiseId);

	}

	@GetMapping("/category")
	public ResponseEntity<List<DataDragAndDrop>> category() {
		LOGGER.info("BreachController :: category");
		return branchService.category();

	}

	/***
	 * Created login method where user is allowed to login with name and password
	 * based on user type.
	 * 
	 * @param loginDto
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<UserDetailsDto> login(@RequestBody LoginDto loginDto) {

		LOGGER.info("BreachController :: loginDto :{} ", loginDto);
		return new ResponseEntity<>(breachService.login(loginDto), HttpStatus.OK);

	}

	@GetMapping("/identifiedBreach")
	public ResponseEntity<List<DataDragAndDrop>> identifiedBreach() {
		LOGGER.info("BreachController :: identifiedBreach");

		return branchService.identifiedBreach();

	}

}