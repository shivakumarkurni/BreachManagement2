package com.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.IdentifiedBeach;

public interface IdentifiedBeachRepository extends JpaRepository<IdentifiedBeach, Integer> {

}
