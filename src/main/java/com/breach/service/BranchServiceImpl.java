package com.breach.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.breach.controller.BreachController;
import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;
import com.breach.entity.Breach;
import com.breach.entity.Erisk;
import com.breach.entity.RiskCalculation;
import com.breach.repository.BreachRepository;
import com.breach.repository.RiskCalculationRepository;
import com.breach.repository.UserDetailsRepository;


@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired UserDetailsRepository userDetailsRepository;
	@Autowired BreachRepository breachRepository;
	@Autowired RiskCalculationRepository riskCalculationRepository;

	final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);


	/* breachEngine making breach and risk calculation
	 * author sairam
	 * @param  franchiseId int
	 * @param  bussinessId int
	 * @param  categoryId int
	 * @param   userId
	 *  */
	@Override
	public ResponseEntity<ResponseDto> breachEngine(BreachEngineInput breachEngineInput) {
		
		LOGGER.info("BranchServiceImpl --> breachEngine franchiseId :{} bussinessId:{}, getCategoryId:{}, identifiedBreachId:{}, userId:{}",breachEngineInput.getFranchiseId(),breachEngineInput.getBussinessId(),breachEngineInput.getCategoryId(),breachEngineInput.getIdentifiedBreachId(),breachEngineInput.getUserId());

		
		//setting breach values

		Breach breach=new Breach();
		breach.setCreatedDate(LocalDateTime.now());
		breach.setBusinessId(breachEngineInput.getBussinessId());
		breach.setCategoryId(breachEngineInput.getCategoryId());
		breach.setFranchiseId(breachEngineInput.getFranchiseId());
		breach.setUserId(breachEngineInput.getUserId());
		
		
		//risk calculation 

		List<RiskCalculation> riskCalculations = riskCalculationRepository.findByFranchiseIdAndBusinessAreaIdAndCategoriseId(breachEngineInput.getFranchiseId(), breachEngineInput.getBussinessId(), breachEngineInput.getCategoryId());
		if(riskCalculations.isEmpty())
			breach.setRisk(Erisk.LOW.name()); 
		else
			breach.setRisk(riskCalculations.get(0).getProfile());
		
		
		//breach saving
		breachRepository.save(breach);
		
		ResponseDto responseDto=new ResponseDto();
		responseDto.setMessage("breach succsessfully created with "+breach.getRisk()+ " level Risk");
		responseDto.setStatusCode(HttpStatus.CREATED.value());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

			
			
		
	}
	
	
	







	@Override
	public ResponseEntity<RiskChecking> riskCheck(BreachEngineInput breachEngineInput) {
		
		List<RiskCalculation> riskCalculations = riskCalculationRepository.findByFranchiseIdAndBusinessAreaIdAndCategoriseId(breachEngineInput.getFranchiseId(), breachEngineInput.getBussinessId(), breachEngineInput.getCategoryId());
		RiskChecking riskChecking=new RiskChecking();
		
		
		
		if(riskCalculations.isEmpty())
			riskChecking.setRisk(Erisk.LOW.name());
		else
			riskChecking.setRisk(riskCalculations.get(0).getProfile());

		return ResponseEntity.status(HttpStatus.OK).body(riskChecking);
	}

	

}
