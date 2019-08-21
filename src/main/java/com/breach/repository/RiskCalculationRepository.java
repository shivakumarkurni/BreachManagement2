package com.breach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.RiskCalculation;

public interface RiskCalculationRepository  extends JpaRepository<RiskCalculation, Integer> {

	public List<RiskCalculation> findByFranchiseIdAndBusinessAreaIdAndCategoriseId(Integer franchiseId, Integer businessAreaId, Integer categoriseId);
	
}
