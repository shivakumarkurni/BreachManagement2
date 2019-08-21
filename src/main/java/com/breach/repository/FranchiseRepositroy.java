package com.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.Franchise;

public interface FranchiseRepositroy extends JpaRepository<Franchise, Integer> {

}
