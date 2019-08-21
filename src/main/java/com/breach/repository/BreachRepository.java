package com.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.Breach;

public interface BreachRepository extends JpaRepository<Breach, Integer> {

}
