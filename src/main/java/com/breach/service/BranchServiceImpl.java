package com.breach.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.breach.controller.BreachController;
import com.breach.dto.BreachEngineInput;
import com.breach.dto.DataDragAndDrop;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskChecking;
import com.breach.entity.Breach;
import com.breach.entity.BussinessArea;
import com.breach.entity.Categories;
import com.breach.entity.Erisk;
import com.breach.entity.Franchise;
import com.breach.entity.IdentifiedBeach;
import com.breach.entity.RiskCalculation;
import com.breach.repository.BreachRepository;
import com.breach.repository.BussinessAreaRepository;
import com.breach.repository.CategoriesRepository;
import com.breach.repository.FranchiseRepository;
import com.breach.repository.IdentifiedBeachRepository;
import com.breach.repository.RiskCalculationRepository;
import com.breach.repository.UserDetailsRepository;


@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired UserDetailsRepository userDetailsRepository;
	@Autowired BreachRepository breachRepository;
	@Autowired RiskCalculationRepository riskCalculationRepository;
	@Autowired FranchiseRepository franchiseRepository;
	@Autowired BussinessAreaRepository bussinessAreaRepository;
	@Autowired CategoriesRepository categoriesRepository;
	@Autowired IdentifiedBeachRepository identifiedBeachRepository;

	final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);


	/**
	 *  breachEngine making breach and risk calculation
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
		
		LOGGER.info("BranchServiceImpl -->breachEngine  done ");

		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

			
			
		
	}
	
	
	





	
	
	/**
	 * checking the risk of perticular combinaton
	 * 
	 * @param franchiseId int
	 * 
	 * @param bussinessId int
	 * 
	 * @param categoryId  int *
	 */

	@Override
	public ResponseEntity<RiskChecking> riskCheck(BreachEngineInput breachEngineInput) {
		LOGGER.info("BranchServiceImpl --> riskCheck franchiseId :{} bussinessId:{}, getCategoryId:{}, identifiedBreachId:{}, userId:{}",breachEngineInput.getFranchiseId(),breachEngineInput.getBussinessId(),breachEngineInput.getCategoryId(),breachEngineInput.getIdentifiedBreachId(),breachEngineInput.getUserId());

		
		List<RiskCalculation> riskCalculations = riskCalculationRepository.findByFranchiseIdAndBusinessAreaIdAndCategoriseId(breachEngineInput.getFranchiseId(), breachEngineInput.getBussinessId(), breachEngineInput.getCategoryId());
		RiskChecking riskChecking=new RiskChecking();
		
		
		
		if(riskCalculations.isEmpty())
			riskChecking.setRisk(Erisk.LOW.name());
		else
			riskChecking.setRisk(riskCalculations.get(0).getProfile());
		LOGGER.info("BranchServiceImpl -->riskCheck  done risk :{}",riskChecking.getRisk());

		return ResponseEntity.status(HttpStatus.OK).body(riskChecking);
	}










	@Override
	public ResponseEntity<List<DataDragAndDrop>> franchise() {
		List<Franchise> franchise = franchiseRepository.findAll();

		List<DataDragAndDrop> dataDragAndDropList=new ArrayList<>();
		for(Franchise frans: franchise) {
			DataDragAndDrop dataDragAndDrop=new DataDragAndDrop();
			dataDragAndDrop.setName(frans.getFranchise());
			dataDragAndDrop.setValue(frans.getFranchiseId());
			dataDragAndDropList.add(dataDragAndDrop);

			
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(dataDragAndDropList);
	}










	@Override
	public ResponseEntity<List<DataDragAndDrop>> bussiness(Integer franchiseId) {
		List<BussinessArea> bussinessList = bussinessAreaRepository.findByFranchiseId(franchiseId);
		
		List<DataDragAndDrop> dataDragAndDropList=new ArrayList<>();
		for(BussinessArea frans: bussinessList) {
			DataDragAndDrop dataDragAndDrop=new DataDragAndDrop();
			dataDragAndDrop.setName(frans.getBussinessName());
			dataDragAndDrop.setValue(frans.getBussinessId());
			dataDragAndDropList.add(dataDragAndDrop);

			
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(dataDragAndDropList);
	}











	@Override
	public ResponseEntity<List<DataDragAndDrop>> category() {
		List<Categories> categories = categoriesRepository.findAll();
		
		List<DataDragAndDrop> dataDragAndDropList=new ArrayList<>();
		for(Categories frans: categories) {
			DataDragAndDrop dataDragAndDrop=new DataDragAndDrop();
			dataDragAndDrop.setName(frans.getCategoriesName());
			dataDragAndDrop.setValue(frans.getCategoriesId());
			dataDragAndDropList.add(dataDragAndDrop);

			
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(dataDragAndDropList);
	}




 





	@Override
	public ResponseEntity<List<DataDragAndDrop>> identifiedBreach() {
		
		List<IdentifiedBeach> identifiedBeachList = identifiedBeachRepository.findAll();
		
		List<DataDragAndDrop> dataDragAndDropList=new ArrayList<>();
		for(IdentifiedBeach frans: identifiedBeachList) {
			DataDragAndDrop dataDragAndDrop=new DataDragAndDrop();
			dataDragAndDrop.setName(frans.getIdentifiedBeachName());
			dataDragAndDrop.setValue(frans.getIdentifiedBeachId());
			dataDragAndDropList.add(dataDragAndDrop);
	
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(dataDragAndDropList);
	}



	

}
