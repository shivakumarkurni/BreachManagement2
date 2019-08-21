package com.credit.getRisk;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.breach.dto.RiskDto;
import com.breach.entity.Breach;
import com.breach.entity.BussinessArea;
import com.breach.entity.Categories;
import com.breach.entity.Franchise;
import com.breach.repository.BreachRepository;
import com.breach.repository.BussinessAreaRepository;
import com.breach.repository.CategoriesRepository;
import com.breach.repository.FranchiseRepositroy;
import com.breach.service.BreachServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestBreachServiceImpl {

	@Mock
	private BreachRepository breachRepository;

	@Mock
	private FranchiseRepositroy franchiseRepositroy;

	@Mock
	private BussinessAreaRepository bussinessRepository;

	@Mock
	private CategoriesRepository categoriesRepository;
	
	@InjectMocks
	private BreachServiceImpl breachServiceImpl;


	List<Franchise> franchises = new ArrayList<>();
	List<BussinessArea> bussinessAreas = new ArrayList<>();
	List<Categories> categories = new ArrayList<>();
	List<Breach> breaches = new ArrayList<>();
	List<RiskDto> riskList = new ArrayList<>();
	
	@Before
	public void setip() {
		
		Franchise franchise = new Franchise();
		franchise.setFranchiseId(11);
		franchise.setFranchise("HDFC");
		
		BussinessArea bussinessArea = new BussinessArea();
		bussinessArea.setBussinessId(101);
		bussinessArea.setBussinessName("Core Banking");
		
		Categories categorie =  new Categories();
		categorie.setCategoriesId(1010);
		categorie.setCategoriesName("Banking");
		
		franchises.add(franchise);
		bussinessAreas.add(bussinessArea);
		categories.add(categorie);
		
		Breach breach = new Breach();
		breach.setBreachId(101);
		breach.setBusinessId(101);
		breach.setCategoryId(1010);
		breach.setCreatedDate(LocalDateTime.of(2019, 8, 23, 17, 30));
		breach.setFranchiseId(11);
		breach.setRisk("HIGH");
		breach.setStatus("NEW");
		breach.setUserId(10);
		
		breaches.add(breach);
		
		
		RiskDto riskDto = new RiskDto();
		riskDto.setBreachId(breach.getBreachId());
		riskDto.setFranchise(franchise.getFranchise());
		riskDto.setBusiness(bussinessArea.getBussinessName());
		riskDto.setCategory(bussinessArea.getBussinessName());
		riskDto.setStatus(breach.getStatus());
		riskList.add(riskDto);
	}
	
	@Test
	public void testGetBreachRisk() {
		
		Mockito.when(franchiseRepositroy.findAll()).thenReturn(franchises);
		Mockito.when(bussinessRepository.findAll()).thenReturn(bussinessAreas);
		Mockito.when(categoriesRepository.findAll()).thenReturn(categories);
		Mockito.when(breachRepository.findByRisk(Mockito.anyString())).thenReturn(breaches);
		
		List<RiskDto> expectedResult = breachServiceImpl.getBreachRisk("HIGH");
		assertEquals("NEW", expectedResult.get(0).getStatus().toString());
	}
}
