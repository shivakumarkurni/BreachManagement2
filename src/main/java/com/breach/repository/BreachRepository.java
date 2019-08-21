package com.breach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.breach.entity.Breach;


@Repository
public interface BreachRepository extends JpaRepository<Breach, Integer> {
	
	public List<Breach> findByFranchiseIdAndBusinessIdAndCategoryId(Integer franchiseId, Integer bussinessId, Integer categoryId);
	List<Breach> findByRisk(String risk);
}
