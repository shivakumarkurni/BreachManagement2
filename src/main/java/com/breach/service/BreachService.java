package com.breach.service;

import java.util.List;

import com.breach.dto.RiskDto;

public interface BreachService {

	List<RiskDto> getBreachRisk(String roleType);

}
