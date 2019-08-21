package com.breach.service;

import java.util.List;

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

@Service
public class BreachServiceImpl implements BreachService {
	private final static Logger LOGGER = LoggerFactory.getLogger(BreachController.class);

	@Autowired
	private BreachRepository breachRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<RiskDto> getBreachRisk(String roleType) {
		// TODO Auto-generated method stub
		return null;
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