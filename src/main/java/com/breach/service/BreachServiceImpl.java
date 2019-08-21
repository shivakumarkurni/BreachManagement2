package com.breach.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.breach.controller.BreachController;
import com.breach.dto.LoginDto;
import com.breach.dto.ResponseDto;
import com.breach.dto.RiskDto;
import com.breach.dto.UserDetailsDto;
import com.breach.entity.UserDetails;
import com.breach.entity.UserRole;
import com.breach.exception.BreachException;
import com.breach.repository.BreachRepository;
import com.breach.repository.UserDetailsRepository;
import com.breach.repository.UserRoleRepository;
import com.breach.entity.Breach;
import com.breach.entity.BussinessArea;
import com.breach.entity.Categories;
import com.breach.entity.Franchise;
import com.breach.repository.BussinessAreaRepository;
import com.breach.repository.CategoriesRepository;
import com.breach.repository.FranchiseRepositroy;

/**
 * @author Laxman
 *
 */
@Service
public class BreachServiceImpl implements BreachService {
	private final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);

	@Autowired
	private BreachRepository breachRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private FranchiseRepositroy franchiseRepositroy;

	@Autowired
	private BussinessAreaRepository bussinessRepository;

	@Autowired
	private CategoriesRepository categoriesRepository;

	/**
	 * this method is to get list of breach based on user type i.e.
	 * High/low/medium/Admin
	 * 
	 * @param roleType
	 * @return
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

	@Override
	public UserDetailsDto login(LoginDto loginDto) {

		LOGGER.info("LoginData:" + loginDto.getName() + "Password:" + loginDto.getPassword());
		int userId = 0;
		String name = loginDto.getName();
		String password = loginDto.getPassword();
		UserDetailsDto user = new UserDetailsDto();

		UserDetails userDetails = userDetailsRepository.findByName(name);
		if (userDetails != null) {
			if (userDetails.getPassword().equals(password)) {
				UserRole userRole = userRoleRepository.findByRoleId(userDetails.getRoleId());
				if (userRole != null) {
					userId = userDetails.getUserId();
					user.setUserId(userId);
					user.setUserType(userRole.getRoleType());
					user.setMessage("Login successfully...");
					user.setStatusCode(HttpStatus.OK.value());
				}
			}
		} else {
			throw new BreachException("User does not exist.");
		}
		LOGGER.info("user", user);
		return user;
	}
}