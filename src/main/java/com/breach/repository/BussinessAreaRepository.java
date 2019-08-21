package com.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.BussinessArea;
import java.lang.Integer;
import java.util.List;

public interface BussinessAreaRepository extends JpaRepository<BussinessArea, Integer> {

	public List<BussinessArea> findByFranchiseId(Integer franchiseid);
}
