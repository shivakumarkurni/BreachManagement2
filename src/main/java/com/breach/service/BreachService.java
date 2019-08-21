package com.breach.service;

import java.util.List;

import com.breach.dto.LoginDto;
import com.breach.dto.RiskDto;
import com.breach.dto.UserDetailsDto;

public interface BreachService {

	List<RiskDto> getBreachRisk(String roleType);
	
	public UserDetailsDto login(LoginDto loginDto);

}
