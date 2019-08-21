package com.breach.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breach.dto.RiskDto;
import com.breach.entity.Breach;
import com.breach.entity.BussinessArea;
import com.breach.entity.Categories;
import com.breach.entity.Franchise;
import com.breach.repository.BreachRepository;
import com.breach.repository.BussinessAreaRepository;
import com.breach.repository.CategoriesRepository;
import com.breach.repository.FranchiseRepositroy;

/**
 * @author Laxman
 *
 */
@Service
public class BreachServiceImpl implements BreachService {

	private final static Logger LOGGER = LoggerFactory.getLogger(BreachServiceImpl.class);

	@Autowired
	private BreachRepository breachRepository;

	@Autowired
	private FranchiseRepositroy franchiseRepositroy;

	@Autowired
	private BussinessAreaRepository bussinessRepository;

	@Autowired
	private CategoriesRepository categoriesRepository;

	/**
	 * This
	 * 
	 * @param roleType
	 * @return list
	 */
	@Override
	public List<RiskDto> getBreachRisk(String roleType) {

		LOGGER.info("BreachServiceImpl :: roleType "+roleType);

		List<Franchise> franchises = franchiseRepositroy.findAll();
		List<BussinessArea> bussinessAreas = bussinessRepository.findAll();
		List<Categories> categories = categoriesRepository.findAll();
		
		Map<Integer, String> franchisesMap = new HashMap<>();
		Map<Integer, String> bussinessAreassMap = new HashMap<>();
		Map<Integer, String> categoriesMap = new HashMap<>();

		List<RiskDto> riskList = new ArrayList<>();
		
		for(Franchise franchise : franchises) {
			franchisesMap.put(franchise.getFranchiseId(), franchise.getFranchise());
		}
		
		for(BussinessArea bussiness : bussinessAreas) {
			bussinessAreassMap.put(bussiness.getBussinessId(), bussiness.getBussinessName());
		}
		
		for(Categories categorie : categories) {
			categoriesMap.put(categorie.getCategoriesId(), categorie.getCategoriesName());
		}
		List<Breach> breaches =null;
		if(roleType.equalsIgnoreCase("Admin")) {
			breaches = breachRepository.findAll();
		} else {
			breaches = breachRepository.findByRisk(roleType);
		}
		
		if (breaches != null) {
			for(Breach breach : breaches) {
				RiskDto riskDto = new RiskDto();
				riskDto.setBreachId(breach.getBreachId());
				riskDto.setFranchise(franchisesMap.get(breach.getFranchiseId()));
				riskDto.setBusiness(bussinessAreassMap.get(breach.getBusinessId()));
				riskDto.setCategory(categoriesMap.get(breach.getCategoryId()));
				riskDto.setStatus(breach.getStatus());
				riskList.add(riskDto);
			}
		}
		return riskList;
	}

}
