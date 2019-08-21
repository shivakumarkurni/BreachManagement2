package com.credit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.breach.dto.BreachEngineInput;
import com.breach.dto.ResponseDto;
import com.breach.entity.Breach;
import com.breach.entity.RiskCalculation;
import com.breach.exception.BreachException;
import com.breach.repository.BreachRepository;
import com.breach.repository.RiskCalculationRepository;
import com.breach.repository.UserDetailsRepository;
import com.breach.service.BranchServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceImplTest {

	@InjectMocks
	BranchServiceImpl branchServiceImpl;

	@Mock
	UserDetailsRepository userDetailsRepository;
	@Mock
	BreachRepository breachRepository;
	@Mock
	RiskCalculationRepository riskCalculationRepository;
	
	Breach breach;
	List<Breach> breachList;
	RiskCalculation riskCalculation;
	List<RiskCalculation> riskCalculationList;

	BreachEngineInput breachEngineInput;
	
	@Before
	public void setup() {
		breach=new Breach();
		breachEngineInput=new BreachEngineInput();
		riskCalculation=new RiskCalculation();
		breachList=new ArrayList<>();
		
		riskCalculationList=new ArrayList<>();
		
	}
	
	@Test
	public void breachEngine() {
		
		Mockito.when(riskCalculationRepository.findByFranchiseIdAndBusinessAreaIdAndCategoriseId(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(riskCalculationList);
		Mockito.when(breachRepository.save(breach)).thenReturn(breach);
		ResponseEntity<ResponseDto> actual = branchServiceImpl.breachEngine(breachEngineInput);
		
		Assert.assertEquals(HttpStatus.CREATED.value(), actual.getStatusCodeValue());
		
	}
	
	@Test(expected = BreachException.class)
	public void breachEngineNegative() {
		
		Mockito.when(riskCalculationRepository.findByFranchiseIdAndBusinessAreaIdAndCategoriseId(1, 1, 1)).thenReturn(riskCalculationList);
		Mockito.when(breachRepository.save(breach)).thenReturn(breach);
		ResponseEntity<ResponseDto> actual = branchServiceImpl.breachEngine(breachEngineInput);
		
		
	}

}
