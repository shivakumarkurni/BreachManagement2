package com.breach.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breach.dto.RequestDTO;
import com.breach.dto.ResponseDto;
import com.breach.entity.Breach;
import com.breach.exception.BreachNotFound;
import com.breach.repository.BreachStatusUpdateRepository;

/**
 * 
 * @author shiva
 * 
 * 
 * 
 *
 */

@Service
public class BreachStatusUpdateServiceImpl implements BreachStatusUpdateService {

	private static final Logger logger = LoggerFactory.getLogger(BreachStatusUpdateServiceImpl.class);

	@Autowired
	BreachStatusUpdateRepository breachStatusUpdateRepository;

	/**
	 * This method update the status of the breach like open/close/re-open
	 * 
	 * @param RequestDTO it contains userId,breachId,status
	 * 
	 * @return ResponseDto it contains message and status code
	 *
	 */

	@Override
	public ResponseDto breachUpdate(RequestDTO requestDTO) {

		Integer userId = requestDTO.getUserId();
		Integer breachId = requestDTO.getBreachId();
		String status = requestDTO.getAction();

		logger.info("Inside BreachStatusUpdateServiceImpl:" + "UserId:" + userId + "" + "breachId" + breachId + "status"
				+ status);

		Optional<Breach> breach = breachStatusUpdateRepository.findById(breachId);

		if (breach.isPresent()) {

			breach.get().setStatus(status);
			breachStatusUpdateRepository.save(breach.get());
			ResponseDto responseDto = new ResponseDto();
			responseDto.setMessage("status updated successfully");
			responseDto.setStatusCode(201);

			return responseDto;
		}

		else {
			throw new BreachNotFound("No breach found with the given Id");
		}

	}

}
