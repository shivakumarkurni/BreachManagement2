package com.breach.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breach.dto.RiskDto;
import com.breach.repository.BreachRepository;

@Service
public class BreachServiceImpl implements BreachService {

	@Autowired
	private BreachRepository breachRepository;
	
	@Override
	public List<RiskDto> getBreachRisk(String roleType) {
		// TODO Auto-generated method stub
		return null;
	}

}
