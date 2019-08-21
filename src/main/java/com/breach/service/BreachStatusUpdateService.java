package com.breach.service;

import org.springframework.stereotype.Service;

import com.breach.dto.RequestDTO;
import com.breach.dto.ResponseDto;

@Service
public interface BreachStatusUpdateService {

	public ResponseDto breachUpdate(RequestDTO requestDTO);

}
