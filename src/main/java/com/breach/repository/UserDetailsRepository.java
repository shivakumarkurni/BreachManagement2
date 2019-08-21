package com.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

}
