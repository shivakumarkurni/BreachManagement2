package com.breach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.Breach;

public interface BreachRepository extends JpaRepository<Breach, Integer> {

	List<Breach> findByRisk(String risk);
}
